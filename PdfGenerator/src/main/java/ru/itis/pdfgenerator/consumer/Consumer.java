package ru.itis.pdfgenerator.consumer;

/**
 * created: 23-01-2022 - 17:29
 * project: PdfGenerator
 *
 * @author dinar
 * @version v0.1
 */
public interface Consumer<T> {

    Object receiveMessage(T message) throws Exception;

}
