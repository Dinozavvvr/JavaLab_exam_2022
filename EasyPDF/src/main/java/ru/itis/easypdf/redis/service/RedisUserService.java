package ru.itis.easypdf.redis.service;

import ru.itis.easypdf.model.User;

/**
 * created: 22-01-2022 - 22:08
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
public interface RedisUserService {

    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);

}
