package com.firstline.controller;

import com.firstline.dto.PatientDto;
import com.firstline.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class PatientRestController {

    private final PatientService patientService;

    private final KafkaTemplate<String, PatientDto> kafkaTemplate;

    private final KafkaTemplate<String, String> kafkaTemplateSecond;

    @Autowired
    public PatientRestController(PatientService patientService, KafkaTemplate<String, PatientDto> kafkaTemplate, KafkaTemplate<String, String> kafkaTemplateSecond) {
        this.patientService = patientService;
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateSecond = kafkaTemplateSecond;
    }

    @GetMapping("/publish/{id}")
    public String post(@PathVariable("id") Long id) {
        kafkaTemplate.send("baeldung", patientService.getPatientById(id));
        return "Json published successfully";
    }

    @GetMapping("/publish/message/{message}")
    public String postMessage(@PathVariable("message") String message) {
        kafkaTemplateSecond.send("baeldung", message);
        return "Message published successfully " + message;
    }

    @GetMapping("/{id}")
    public String list(@PathVariable Long id) {

        return patientService.getListStudiesOfPatient(id).toString();
        // return patientService.findAllPatient().toString();
    }

    @ApiOperation(value = "return list patients")
    @GetMapping("/list")
    public List<PatientDto> getListApplication() {
        return patientService.getAllPatients();
    }

    @ApiOperation(value = "create patient")
    @PostMapping
    public Long createApplication(@RequestBody PatientDto patientDto) {
        return patientService.createPatient(patientDto);
    }

}
