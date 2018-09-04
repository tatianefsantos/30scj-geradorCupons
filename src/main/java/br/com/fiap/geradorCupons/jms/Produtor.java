package br.com.fiap.geradorCupons.jms;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Produtor {
	final static Logger logger = Logger.getLogger(Produtor.class);

	@Autowired
    JmsTemplate jmsTemplate;
	
	public void send(String msg){
		logger.info("Produzindo: " + msg);
		jmsTemplate.convertAndSend("queue.cupom", msg);
	}
	
}