package ru.itis.easypdf.service;

import com.auth0.jwt.interfaces.Claim;
import ru.itis.easypdf.dto.TokenDto;
import ru.itis.easypdf.model.User;

import java.util.Map;

/**
 * created: 22-01-2022 - 16:51
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
public interface TokenService {

    TokenDto generateAccessToken(User user);

    Map<String, Claim> verifyAccessToken(String token);

}