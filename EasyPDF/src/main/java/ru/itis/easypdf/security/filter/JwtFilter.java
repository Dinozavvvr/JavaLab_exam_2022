package ru.itis.easypdf.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.easypdf.redis.service.JwtBlackListRedisService;
import ru.itis.easypdf.security.authentication.JwtAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created: 22-01-2022 - 17:14
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Value("${headers.access.token}")
    private String ACCESS_TOKEN;

    @Autowired
    private JwtBlackListRedisService redisService;

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain)
            throws IOException, ServletException {

        String token = request.getHeader(ACCESS_TOKEN);

        if (token != null) {
            if (redisService.exists(token)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            JwtAuthentication jwtAuthentication = new JwtAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
        } else {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
