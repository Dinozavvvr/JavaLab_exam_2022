package ru.itis.pdfgenerator.consumer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.itis.pdfgenerator.configuration.RabbitMqConfiguration;
import ru.itis.pdfgenerator.dto.BudgetPdfDto;
import ru.itis.pdfgenerator.dto.PdfRequestDto;
import ru.itis.pdfgenerator.generator.Generator;

/**
 * created: 24-01-2022 - 13:42
 * project: PdfGenerator
 *
 * @author dinar
 * @version v0.1
 */
@Component
@RequiredArgsConstructor
public class BudgetPdfConsumer extends DefaultPdfConsumer<BudgetPdfDto> {

    private final RabbitTemplate rabbitTemplate;

    private final Generator generator;

    private final Logger logger = LoggerFactory.getLogger(OtchisleniePdfConsumer.class);

    @Override
    @RabbitListener(queues = RabbitMqConfiguration.BUDGET_QUEUE_NAME)
    public String receiveMessage(PdfRequestDto<BudgetPdfDto> dto) throws Exception {
        logger.info("Received message: " + dto.toString());

        return super.receiveMessage(dto);
    }

    @Override
    public void configure(ConsumerConfigurer consumerConfigurer) {
        consumerConfigurer
                .setGenerator(generator)
                .setRabbitTemplate(rabbitTemplate)
                .setTemplate("budget.ftlh")
                .build();
    }

}