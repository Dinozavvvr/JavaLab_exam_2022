package ru.itis.pdfgenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class PdfResponseDto implements Serializable {

    private Long userId;

    private Long createdAt;

    private byte[] data;

}
