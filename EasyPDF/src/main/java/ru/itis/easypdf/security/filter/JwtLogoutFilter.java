package ru.itis.easypdf.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.easypdf.redis.service.JwtBlackListRedisService;
import ru.itis.easypdf.service.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created: 22-01-2022 - 22:27
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Component
@RequiredArgsConstructor
public class JwtLogoutFilter extends OncePerRequestFilter {

    @Value("${headers.access.token}")
    private String ACCESS_TOKEN;

    private final RequestMatcher logoutMatcher =
            new AntPathRequestMatcher("/logout", "GET");

    private final JwtBlackListRedisService blackListService;

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        if (logoutMatcher.matches(request)) {
            String accessToken = request.getHeader(ACCESS_TOKEN);

            if (accessToken != null && tokenService.verifyAccessToken(accessToken) != null) {
                blackListService.add(accessToken);
                SecurityContextHolder.clearContext();
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            return;
        }

        filterChain.doFilter(request, response);
    }
}
