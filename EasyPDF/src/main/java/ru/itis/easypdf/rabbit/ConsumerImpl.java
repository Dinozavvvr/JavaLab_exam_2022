package ru.itis.easypdf.rabbit;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.itis.easypdf.configuration.RabbitMqConfiguration;
import ru.itis.easypdf.dto.PdfResponseDto;
import ru.itis.easypdf.model.Document;
import ru.itis.easypdf.service.DocumentService;

/**
 * created: 23-01-2022 - 17:57
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Component
@RequiredArgsConstructor
public class ConsumerImpl implements Consumer<PdfResponseDto> {

    private final Logger logger = LoggerFactory.getLogger(ConsumerImpl.class);

    private final DocumentService documentService;

    @Override
    @RabbitListener(queues = RabbitMqConfiguration.REPLY_QUEUE_NAME, concurrency = "3")
    public void receiveMessage(PdfResponseDto message) {
        Document document = documentService.save(message);

        logger.info("Saved document: " + document);
    }

}
