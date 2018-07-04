package com.mgoode.eplatformorderimport.consumer;

import com.mgoode.eplatformorderimport.entities.EDIImportResult;
import com.mgoode.eplatformorderimport.entities.EDIOrder;
import com.mgoode.eplatformorderimport.producer.Producer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@EnableRabbit
public class Consumer implements MessageListener {

    int count;

    @Autowired
    ConsumerConfiguration consumerConfiguration;

    @Autowired
    Producer producer;

    @RabbitListener(queues="${orderbroker.rabbitmq.queue}")
    public void receive(EDIOrder ediOrder ) {
        try {
            // we have an ediOrder, import it to EP
            System.out.println(count++ + " Received " + ediOrder.getOrderUUID() + "     " + ediOrder.getCont_no() + "   line count : " + ediOrder.getSkuLineList().size() );

            EDIImportResult ediImportResult = new EDIImportResult();
            ediImportResult.setOrderUUID( ediOrder.getOrderUUID() );
            ediImportResult.setStatus("EX");

            System.out.println("Sending import result back to OrderBroker " + ediImportResult.getOrderUUID());
            producer.sendOrderMessage(ediImportResult);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        String body = new String( message.getBody());
    }
}
