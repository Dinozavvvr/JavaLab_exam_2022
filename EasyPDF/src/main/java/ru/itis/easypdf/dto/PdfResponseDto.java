package ru.itis.easypdf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created: 23-01-2022 - 17:54
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdfResponseDto {

    private Long userId;

    private Long createdAt;

    private byte[] data;

}
