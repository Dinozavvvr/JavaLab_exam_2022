package ru.itis.easypdf.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import ru.itis.easypdf.dto.TokenDto;
import ru.itis.easypdf.model.User;
import ru.itis.easypdf.utils.JwtTokenUtils;

import java.util.Date;
import java.util.Map;

/**
 * created: 22-01-2022 - 16:53
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public TokenDto generateAccessToken(User user) {
        return new TokenDto(JWT.create()
                .withSubject(user.getId().toString())
                .withClaim(JwtTokenUtils.STATE, user.getState().toString())
                .withClaim(JwtTokenUtils.LOGIN, user.getLogin())
                .withClaim(JwtTokenUtils.ROLE, user.getRole().toString())
                .withClaim(JwtTokenUtils.ID, user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(
                        new Date(System.currentTimeMillis() + JwtTokenUtils.ACCESS_TOKEN_LIFE_TIME))
                .sign(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY)));
    }

    @Override
    public Map<String, Claim> verifyAccessToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY))
                    .build().verify(token);
            return decodedJWT.getExpiresAt()
                    .after(new Date()) ? decodedJWT.getClaims() : null;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

}
