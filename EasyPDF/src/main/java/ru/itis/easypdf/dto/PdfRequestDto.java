package ru.itis.easypdf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created: 24-01-2022 - 12:56
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdfRequestDto {

    private Long id;

    private Object payload;

}
