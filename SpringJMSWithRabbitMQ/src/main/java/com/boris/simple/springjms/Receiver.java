package com.boris.simple.springjms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private static final Log log = LogFactory.getLog(Receiver.class);
    @JmsListener(destination = "customDestination")
    public void receive(Email message) {
        log.info("Received " + message);
    }
    /*
    JmsTemplate по умолчанию queue, а не topic, поэтому будет юзаться один из этих методов случайным методом.
    Сделать его topic низя из-за преколов RabbitMQ
     */
    @JmsListener(destination = "customDestination")
    public void receive2(Email message) {
        log.info("!!!!!!!!Received " + message);
    }


}
