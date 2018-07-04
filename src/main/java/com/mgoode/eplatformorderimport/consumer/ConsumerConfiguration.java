package com.mgoode.eplatformorderimport.consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.stereotype.Component;

@Configuration
public class ConsumerConfiguration implements RabbitListenerConfigurer {


    @Value("${orderbroker.rabbitmq.queue}")
    String queueName;

    @Value("${orderbroker.rabbitmq.exchange}")
    String exchangeName;

    @Value("${orderbroker.rabbitmq.routingkey}")
    String routingKey;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        return mappingJackson2MessageConverter;
    }

    @Bean
    public Exchange eventExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public Binding binding(Queue queue, Exchange eventExchange) {
        return BindingBuilder
                .bind(queue)
                .to(eventExchange)
                .with(routingKey)
                .noargs();
    }

    @Bean
    public Consumer consumer() {
        return new Consumer();
    }

}
