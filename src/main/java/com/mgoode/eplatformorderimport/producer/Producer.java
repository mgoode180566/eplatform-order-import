package com.mgoode.eplatformorderimport.producer;

import com.mgoode.eplatformorderimport.entities.EDIImportResult;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private ProducerConfiguration producerConfiguration;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOrderMessage(EDIImportResult ediImportResult) {
        try {
            rabbitTemplate.convertAndSend("edi-import-exchange", "order-import.*", ediImportResult);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
