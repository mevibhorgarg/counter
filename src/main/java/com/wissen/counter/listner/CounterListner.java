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

    public static final String host="http://localhost:";
    public static final String counter = "/counter";
    public static final Long incrementCount= 1000000L;

    @KafkaListener(groupId = "tinyurl-1", topics = "RequestCounter", containerFactory = "kafkaListenerContainerFactory")
    public void getMsgFromTopic(String data) {
        CounterRequest counterRequest = new CounterRequest();
        counterRequest.setFirst(first);
        counterRequest.setLast(last);
        first= last;
        last= last+incrementCount;
        System.out.println(data);
        String url = host+ data + counter;
        template.postForObject(url, counterRequest, String.class);
    }

}
