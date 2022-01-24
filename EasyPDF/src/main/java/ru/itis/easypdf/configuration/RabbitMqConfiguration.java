package ru.itis.easypdf.configuration;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.itis.easypdf.dto.GrantPdfDto;

import java.util.concurrent.TimeUnit;

/**
 * created: 23-01-2022 - 13:36
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */
@Configuration
public class RabbitMqConfiguration {

    public static final String TOPIC_EXCHANGE_NAME = "pdf-exchange";

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
    public Queue grantQueue() {
        return new Queue(GRANT_QUEUE_NAME, false);
    }

    @Bean
    public Queue budgetQueue() {
        return new Queue(BUDGET_QUEUE_NAME, false);
    }

    @Bean
    public Queue otchislenQueue() {
        return new Queue(OTCHISLEN_QUEUE_NAME, false);
    }

    @Bean
    public Queue replyQueue() {
        return new Queue(REPLY_QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Binding grantBinding(Queue grantQueue, TopicExchange exchange) {
        return BindingBuilder.bind(grantQueue).to(exchange).with(grantQueue.getName());
    }

    @Bean
    public Binding budgetBinding(Queue budgetQueue, TopicExchange exchange) {
        return BindingBuilder.bind(budgetQueue).to(exchange).with(budgetQueue.getName());
    }

    @Bean
    public Binding otchislenBinding(Queue otchislenQueue, TopicExchange exchange) {
        return BindingBuilder.bind(otchislenQueue).to(exchange).with(otchislenQueue.getName());
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}