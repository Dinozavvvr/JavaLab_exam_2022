package ru.itis.pdfgenerator.generator;

/**
 * created: 24-01-2022 - 11:45
 * project: PdfGenerator
 *
 * @author dinar
 * @version v0.1
 */
public interface Generator {

    byte[] generate(String template, Object data) throws Exception;

}
