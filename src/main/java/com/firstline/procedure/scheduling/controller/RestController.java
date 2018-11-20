package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = "/rest")
public class RestController {

    @Autowired
    PatientService patientService;

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
