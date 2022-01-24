package ru.itis.pdfgenerator.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itis.pdfgenerator.generator.PdfGenerator;

/**
 * created: 23-01-2022 - 17:29
 * project: PdfGenerator
 *
 * @author dinar
 * @version v0.1
 */
@Configuration
public class RabbitMqConfiguration {

    public static final String GRANT_QUEUE_NAME = "pdf-grant";
    public static final String BUDGET_QUEUE_NAME = "pdf-budget";
    public static final String OTCHISLEN_QUEUE_NAME = "pdf-otchislen";

    public static final String REPLY_QUEUE_NAME = "pdf-reply";

    @Value("${rabbit.connection.port}")
    private int port;

    @Value("${rabbit.connection.host}")
    private String host;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host, port);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}