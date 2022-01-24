package ru.itis.easypdf.redis.service;

/**
 * created: 22-01-2022 - 22:10
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
public interface JwtBlackListRedisService {

    void add(String token);

    boolean exists(String token);

}

