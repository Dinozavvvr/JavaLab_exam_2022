package ru.itis.easypdf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.easypdf.dto.CreateUserDto;
import ru.itis.easypdf.dto.UserLoginDto;
import ru.itis.easypdf.service.UserService;

import javax.annotation.security.PermitAll;

/**
 * created: 22-01-2022 - 15:22
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PermitAll
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody CreateUserDto userDto) {
        return ResponseEntity.ok(userService.createNewUser(userDto));
    }

    @PermitAll
    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.login(userLoginDto));
    }

}
