package com.firstline.listener;

import com.firstline.dto.PatientDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "baeldung", groupId = "patient",
    containerFactory = "jsonKafkaListenerContainerFactory")
    public void consumeJson(PatientDto patientDto) {
        System.out.println("Consumed JSON Message" + patientDto);
    }

    @KafkaListener(topics = "baeldung", groupId = "simple",
            containerFactory = "simpleKafkaListenerContainerFactory")
    public void consume(String string) {
        System.out.println("Consumed JSON Message" + string);
    }

}
