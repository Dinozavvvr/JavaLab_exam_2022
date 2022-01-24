package ru.itis.easypdf.redis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

/**
 * created: 22-01-2022 - 22:06
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("user")
public class RedisUser {

    @Id
    private String id;

    private List<String> tokens;

    private Long userId;

}