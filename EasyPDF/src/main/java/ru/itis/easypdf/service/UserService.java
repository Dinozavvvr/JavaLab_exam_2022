package ru.itis.easypdf.service;

import ru.itis.easypdf.dto.CreateUserDto;
import ru.itis.easypdf.dto.TokenDto;
import ru.itis.easypdf.dto.UserLoginDto;
import ru.itis.easypdf.model.User;

/**
 * created: 22-01-2022 - 15:28
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
public interface UserService {

    User createNewUser(CreateUserDto userDto);

    TokenDto login(UserLoginDto userLoginDto);

}
