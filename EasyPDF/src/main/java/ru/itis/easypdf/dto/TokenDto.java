package ru.itis.easypdf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created: 22-01-2022 - 17:19
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    private String token;

}
