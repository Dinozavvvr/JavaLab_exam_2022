package ru.itis.easypdf.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.easypdf.dto.CreateUserDto;
import ru.itis.easypdf.dto.TokenDto;
import ru.itis.easypdf.dto.UserLoginDto;
import ru.itis.easypdf.model.User;
import ru.itis.easypdf.redis.service.RedisUserService;
import ru.itis.easypdf.repository.UserRepository;

/**
 * created: 22-01-2022 - 15:31
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final RedisUserService redisUserService;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    @Override
    public User createNewUser(CreateUserDto userDto) {
        User user = User.builder()
                .login(userDto.getLogin())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .state(User.State.ACTIVE)
                .role(User.Role.USER)
                .build();

        logger.info("Created new User: " + user.toString());

        user = userRepository.save(user);

        logger.info("Created new User. Id = " + user.getId());

        return user;
    }

    @Override
    public TokenDto login(UserLoginDto user) {
        User loginUser = userRepository.findByLogin(user.getLogin())
                .orElseThrow(() ->
                        new UsernameNotFoundException("incorrect credentials"));

        if (passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
            TokenDto tokenDto = tokenService.generateAccessToken(loginUser);

            redisUserService.addTokenToUser(loginUser, tokenDto.getToken());

            return tokenDto;
        } else {
            throw new UsernameNotFoundException("incorrect credentials");
        }
    }

}
