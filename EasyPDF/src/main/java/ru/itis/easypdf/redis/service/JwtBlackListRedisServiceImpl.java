package ru.itis.easypdf.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.easypdf.redis.repository.BlackListRepository;

/**
 * created: 22-01-2022 - 22:10
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class JwtBlackListRedisServiceImpl implements JwtBlackListRedisService {

    @Autowired
    private BlackListRepository blackListRepository;

    @Override
    public void add(String token) {
        blackListRepository.save(token);
    }

    @Override
    public boolean exists(String token) {
        return blackListRepository.exists(token);
    }

}
