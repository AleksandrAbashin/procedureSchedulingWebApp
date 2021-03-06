package com.firstline.controller;

import com.firstline.dto.StudyDto;
import com.firstline.service.PatientService;
import com.firstline.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/study")
public class StudyController {

    private final StudyService studyService;
    private final PatientService patientService;

    @Autowired
    public StudyController(StudyService studyService, PatientService patientService) {
        this.studyService = studyService;
        this.patientService = patientService;
    }

    @GetMapping
    public String editorPage(Model model) {
        model.addAttribute("studyDto", new StudyDto());
        model.addAttribute("patients", patientService.getAllPatients());
        return "addStudy";
    }


    @PostMapping
    public String createStudy(@ModelAttribute StudyDto studyDto) {
        studyService.createStudy(studyDto);
        return "addStudy";
    }

    @GetMapping("/update/{id}")
    public String updateStudy(@PathVariable Long id, Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("studyDto", studyService.updateStudy(studyService.getStudyById(id)));
        return "addStudy";
    }
}
