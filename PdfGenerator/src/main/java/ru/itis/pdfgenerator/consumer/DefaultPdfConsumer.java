package ru.itis.pdfgenerator.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.pdfgenerator.configuration.RabbitMqConfiguration;
import ru.itis.pdfgenerator.dto.PdfRequestDto;
import ru.itis.pdfgenerator.dto.PdfResponseDto;
import ru.itis.pdfgenerator.generator.Generator;

import javax.annotation.PostConstruct;

/**
 * created: 24-01-2022 - 10:46
 * project: PdfGenerator
 *
 * @author dinar
 * @version v0.1
 */
@AllArgsConstructor
@NoArgsConstructor
public abstract class DefaultPdfConsumer<T> implements Consumer<PdfRequestDto<T>> {

    @Autowired
    private ObjectMapper objectMapper;

    private RabbitTemplate rabbitTemplate;

    private Generator generator;

    private String template;

    @Override
    public String receiveMessage(PdfRequestDto<T> dto) throws Exception {

        PdfResponseDto responsePdf = PdfResponseDto.builder()
                .data(generator.generate(template, dto.getPayload()))
                .createdAt(System.currentTimeMillis())
                .userId(dto.getId())
                .build();

        rabbitTemplate.convertAndSend(RabbitMqConfiguration.REPLY_QUEUE_NAME,
                responsePdf);

        return objectMapper.writeValueAsString(responsePdf);
    }

    @PostConstruct
    private void configure() {
        configure(new ConsumerConfigurer());
    }

    public abstract void configure(ConsumerConfigurer consumerConfigurer);

    @Getter
    public class ConsumerConfigurer {

        private RabbitTemplate rabbitTemplate;

        private Generator generator;

        private String template;

        public void build() {
            DefaultPdfConsumer.this.rabbitTemplate = rabbitTemplate;
            DefaultPdfConsumer.this.generator = generator;
            DefaultPdfConsumer.this.template = template;
        }

        public ConsumerConfigurer setRabbitTemplate(RabbitTemplate rabbitTemplate) {
            this.rabbitTemplate = rabbitTemplate;
            return this;
        }

        public ConsumerConfigurer setGenerator(Generator generator) {
            this.generator = generator;
            return this;
        }

        public ConsumerConfigurer setTemplate(String template) {
            this.template = template;
            return this;
        }
    }

}
