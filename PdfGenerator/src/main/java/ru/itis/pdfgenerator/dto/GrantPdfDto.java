package ru.itis.pdfgenerator.dto;

import lombok.Data;

/**
 * created: 23-01-2022 - 17:30
 * project: PdfGenerator
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
