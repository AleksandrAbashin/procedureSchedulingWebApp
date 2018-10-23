package com.firstline.procedureScheduling.controller;

import com.firstline.procedureScheduling.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;


}
