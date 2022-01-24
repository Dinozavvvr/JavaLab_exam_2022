package ru.itis.pdfgenerator.configuration;

import freemarker.cache.FileTemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * created: 24-01-2022 - 12:45
 * project: PdfGenerator
 *
 * @author dinar
 * @version v0.1
 */
@Configuration
public class FreemarkerConfiguration {

    @Bean
    public freemarker.template.Configuration configuration() throws IOException {
        freemarker.template.Configuration cfg =
                new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_31);
        FileTemplateLoader templateLoader = new FileTemplateLoader(
                new File("/src/main/resources/templates"));
        cfg.setTemplateLoader(templateLoader);
        return cfg;
    }

}
