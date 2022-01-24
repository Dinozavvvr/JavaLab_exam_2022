package ru.itis.easypdf.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.itis.easypdf.configuration.RabbitMqConfiguration;
import ru.itis.easypdf.dto.*;
import ru.itis.easypdf.model.Document;
import ru.itis.easypdf.repository.DocumentRepository;

/**
 * created: 23-01-2022 - 16:47
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Service
@RequiredArgsConstructor
public class PdfDocumentServiceImpl implements DocumentService {

    private final RabbitTemplate rabbitTemplate;

    private final DocumentRepository documentRepository;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public <T> byte[] generateDocument(T document, Long userId) {
        String queue;

        if (GrantPdfDto.class.equals(document.getClass())) {
            queue = RabbitMqConfiguration.GRANT_QUEUE_NAME;
        } else if (BudgetPdfDto.class.equals(document.getClass())) {
            queue = RabbitMqConfiguration.BUDGET_QUEUE_NAME;
        } else if (OtchisleniePdfDto.class.equals(document.getClass())) {
            queue = RabbitMqConfiguration.OTCHISLEN_QUEUE_NAME;
        } else {
            throw new UnsupportedOperationException("unsupported class");
        }


        PdfRequestDto requestDto = PdfRequestDto.builder()
                .id(userId)
                .payload(document)
                .build();

        PdfResponseDto responseDto = objectMapper.readValue((String) rabbitTemplate.convertSendAndReceive(
                RabbitMqConfiguration.TOPIC_EXCHANGE_NAME, queue, requestDto), PdfResponseDto.class);

        if (responseDto != null) {
            return responseDto.getData();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Document save(PdfResponseDto message) {
        Document document = Document.builder()
                .userId(message.getUserId())
                .data(message.getData())
                .createdAt(System.currentTimeMillis())
                .build();

        return documentRepository.save(document);
    }

}