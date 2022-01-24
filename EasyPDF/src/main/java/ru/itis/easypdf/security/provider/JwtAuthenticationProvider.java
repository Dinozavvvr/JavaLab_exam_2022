package ru.itis.easypdf.security.provider;

import com.auth0.jwt.interfaces.Claim;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.itis.easypdf.model.User;
import ru.itis.easypdf.security.authentication.JwtAuthentication;
import ru.itis.easypdf.security.details.UserDetailsImpl;
import ru.itis.easypdf.service.TokenService;
import ru.itis.easypdf.utils.JwtTokenUtils;

import java.util.Map;

/**
 * created: 22-01-2022 - 16:10
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    public final TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        Map<String, Claim> claims = tokenService.verifyAccessToken(token);

        JwtAuthentication jwtAuthentication = new JwtAuthentication(token);

        if (claims != null) {
            User user = User.builder()
                    .login(claims.get(JwtTokenUtils.LOGIN).asString())
                    .state(claims.get(JwtTokenUtils.STATE).as(User.State.class))
                    .role(claims.get(JwtTokenUtils.ROLE).as(User.Role.class))
                    .id(claims.get(JwtTokenUtils.ID).asLong())
                    .build();
            jwtAuthentication.setAuthenticated(true);
            jwtAuthentication.setUserDetails(new UserDetailsImpl(user));
        } else {
            SecurityContextHolder.clearContext();
            return null;
        }
        return jwtAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.isAssignableFrom(authentication);
    }

}