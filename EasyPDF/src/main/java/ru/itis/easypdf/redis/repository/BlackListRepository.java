package ru.itis.easypdf.redis.repository;

/**
 * created: 22-01-2022 - 22:08
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
public interface BlackListRepository {

    void save(String token);

    boolean exists(String token);

}
