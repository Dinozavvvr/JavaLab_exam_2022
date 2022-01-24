package ru.itis.easypdf.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.easypdf.model.User;
import ru.itis.easypdf.redis.model.RedisUser;
import ru.itis.easypdf.redis.repository.RedisUserRepository;
import ru.itis.easypdf.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * created: 22-01-2022 - 22:09
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class RedisUserServiceImpl implements RedisUserService {

    @Autowired
    private JwtBlackListRedisService blackListRedisService;

    @Autowired
    private RedisUserRepository redisUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addTokenToUser(User user, String token) {
        String redisId = user.getRedisId();

        RedisUser redisUser;
        if (redisId != null) {
            redisUser = redisUserRepository.findById(redisId)
                    .orElseThrow(IllegalStateException::new);

            if (redisUser.getTokens() == null) {
                redisUser.setTokens(new ArrayList<>());
            }
            redisUser.getTokens().add(token);
        } else {
            redisUser = RedisUser.builder()
                    .userId(user.getId())
                    .tokens(List.of(token))
                    .build();
        }

        redisUserRepository.save(redisUser);
        user.setRedisId(redisUser.getId());
        userRepository.save(user);
    }

    @Override
    public void addAllTokensToBlackList(User user) {
        if (user.getRedisId() != null) {
            RedisUser redisUser = redisUserRepository.findById(user.getRedisId())
                    .orElseThrow(IllegalStateException::new);

            redisUser.getTokens().forEach(token -> blackListRedisService.add(token));
            redisUser.getTokens().clear();
            redisUserRepository.save(redisUser);
        }
    }
}