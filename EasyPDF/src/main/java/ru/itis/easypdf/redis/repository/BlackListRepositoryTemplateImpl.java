package ru.itis.easypdf.redis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * created: 22-01-2022 - 22:20
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Repository
public class BlackListRepositoryTemplateImpl implements BlackListRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String token) {
        redisTemplate.opsForValue().set(token, "");
    }

    @Override
    public boolean exists(String token) {
        Boolean hasToken = redisTemplate.hasKey(token);
        return hasToken != null && hasToken;
    }

}
