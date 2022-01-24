package ru.itis.pdfgenerator.dto;

import lombok.Data;

/**
 * created: 24-01-2022 - 10:41
 * project: PdfGenerator
 *
 * @author dinar
 * @version v0.1
 */
@Data
public class PdfRequestDto<T> {

    private Long id;

    private T payload;

}
