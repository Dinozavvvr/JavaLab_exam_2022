package ru.itis.easypdf.dto;

import lombok.Data;

/**
 * created: 22-01-2022 - 15:25
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */

@Data
public class CreateUserDto {

    private String login;

    private String password;

}
