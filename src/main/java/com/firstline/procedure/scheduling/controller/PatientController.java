package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;


}
