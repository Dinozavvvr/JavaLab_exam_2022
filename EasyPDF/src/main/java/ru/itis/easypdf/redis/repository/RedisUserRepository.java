package ru.itis.easypdf.redis.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.easypdf.redis.model.RedisUser;

/**
 * created: 22-01-2022 - 22:07
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
public interface RedisUserRepository extends KeyValueRepository<RedisUser, String> {
}

