package ru.itis.pdfgenerator.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.html2pdf.HtmlConverter;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * created: 24-01-2022 - 12:15
 * project: PdfGenerator
 *
 * @author dinar
 * @version v0.1
 */
@Component
@RequiredArgsConstructor
public class PdfGenerator implements Generator {

    private final ObjectMapper objectMapper;

    private final Configuration configuration;

    @Override
    public byte[] generate(String filename, Object obj) throws Exception {
        ByteArrayOutputStream htmlOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(htmlOutputStream);

        Map<String, Object> data = objectMapper.convertValue(obj, Map.class);

        Template template = configuration.getTemplate(filename);
        template.process(data, outputStreamWriter);

        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();

        HtmlConverter.convertToPdf(new ByteArrayInputStream(htmlOutputStream.toByteArray()),
                pdfOutputStream);

        return pdfOutputStream.toByteArray();
    }

}
