package ru.itis.easypdf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * created: 22-01-2022 - 17:48
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Data
public class GrantPdfDto {

    private String name;

    private String surname;

    private String patronymic;

    private String from;

    private String to;

}
