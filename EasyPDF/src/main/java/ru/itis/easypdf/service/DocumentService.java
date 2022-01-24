package ru.itis.easypdf.service;

import ru.itis.easypdf.dto.PdfResponseDto;
import ru.itis.easypdf.model.Document;

/**
 * created: 23-01-2022 - 16:40
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
public interface DocumentService {

    <T> byte[] generateDocument(T document, Long userId);

    Document save(PdfResponseDto document);

}
