package com.wissen.counter.listner;

import com.wissen.counter.model.CounterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class CounterListner {

    @Autowired
    RestTemplate template;

    Long first=1000000L;
    Long last=2000000L;

    @KafkaListener(groupId = "tinyurl-1", topics = "RequestCounter", containerFactory = "kafkaListenerContainerFactory")
    public void getMsgFromTopic(String data) {
        CounterRequest counterRequest = new CounterRequest();
        counterRequest.setFirst(first);
        counterRequest.setLast(last);
        first= last;
        last= last+1000000L;
        System.out.println(data);
        String url = "http://localhost:"+ data + "/counter";
        template.postForObject(url, counterRequest, String.class);
    }

}
