package com.firstline.controller;

import com.firstline.dto.PatientDto;
import com.firstline.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class PatientRestController {

    final PatientService patientService;

    @Autowired
    public PatientRestController(PatientService patientService) {
        this.patientService = patientService;
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
