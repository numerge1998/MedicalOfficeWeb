package com.example.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class PracticalTipSender {

    private static final Logger log =  LoggerFactory.getLogger(PracticalTipSender.class);
    private final RabbitTemplate rabbitTemplate;
    ArrayList<PracticalTipMessage> fisier = initFisier();
    int i=0;

    public ArrayList<PracticalTipMessage> initFisier() throws IOException, ParseException {
        ArrayList<PracticalTipMessage> fisier1 = new ArrayList<>();
        File file = new File("E:\\an4\\sem1\\SD\\Tema2\\activity.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String[] tokens ;
        SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while ((st = br.readLine()) != null) {
            tokens = st.split("\t\t");
            Date dateTime = formatter6.parse(tokens[0]);
            long mili = dateTime.getTime();
            Date dateTime1 = formatter6.parse(tokens[1]);
            long mili1 = dateTime1.getTime();
            UUID uid = UUID.fromString("813db233-2d3a-49f2-b5c3-d1cab10d96d9");
            fisier1.add(new PracticalTipMessage(uid,mili,mili1,tokens[2]));
        }
        return fisier1;
    }

    public PracticalTipSender(RabbitTemplate rabbitTemplate) throws IOException, ParseException {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 1500L)
    public void sendPracticalTip() throws IOException {
        rabbitTemplate.convertAndSend(ProducerApplication.EXCHANGE_NAME, ProducerApplication.ROUTING_KEY, this.fisier.get(i));
        if(i < this.fisier.size() - 1)
            i++;
        else
            return ;
        log.info("Practical tip sent");
    }
}
