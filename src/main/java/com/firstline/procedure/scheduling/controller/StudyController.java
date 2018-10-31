package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.dto.StudyDto;
import com.firstline.procedure.scheduling.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/study")
public class StudyController {
    @Autowired
    private StudyService studyService;


    @GetMapping
    public String editorPage(Model model) {
        model.addAttribute("studyDto", new StudyDto());
        return "addStudy";
    }


    @PostMapping
    public String createStudy(@ModelAttribute StudyDto studyDto) {
        studyService.createStudy(studyDto);
        return "addStudy";
    }

    @PutMapping("/update/{id}")
    public String updateStudy(@PathVariable Long id, @ModelAttribute StudyDto studyDto) {

       /* model.addAttribute("studyDto",studyService.getStudyById(id));
        studyServiceImp.updateStudy(studyDto);*/
        return "updateStudy";
    }

    @DeleteMapping(value = "/study/delete/{article_id}")
    public String deletePatient(@PathVariable("article_id") Long id) {
       studyService.deleteStudy(id);
        return "addStudy";
    }


}
