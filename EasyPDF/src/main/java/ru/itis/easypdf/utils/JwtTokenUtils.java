package ru.itis.easypdf.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * created: 22-01-2022 - 17:03
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Component
public class JwtTokenUtils {

    public static String SECRET_KEY;

    public static final String LOGIN = "login";

    public static final String STATE = "state";

    public static final String ROLE = "role";

    public static final String ID = "id";

    public static Long ACCESS_TOKEN_LIFE_TIME = 1000 * 60 * 60 * 30L;          // default 30 minutes

    @Value("${jwt.access.lifetime}")
    public void setAccessTokenLifeTime(Long accessTokenLifeTime) {
        ACCESS_TOKEN_LIFE_TIME = accessTokenLifeTime;
    }

    @Value("${jwt.secret.key}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

}
