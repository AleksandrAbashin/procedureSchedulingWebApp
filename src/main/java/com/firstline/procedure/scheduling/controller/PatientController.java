package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.dto.StudyDto;
import com.firstline.procedure.scheduling.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private static int currentPage = 1;
    private static int pageSize = 5;

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String prepareView(Model model) {
        model.addAttribute("msg", "Spring quick start!!");
        return "my-page";
    }


    @GetMapping
    public String editorPage(Model model) {
            model.addAttribute("patientDto", new PatientDto());
        return "addPatient";
    }


    @PostMapping
    public String createPatient(@ModelAttribute @Valid PatientDto patientDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addPatient";
        }  else {
            patientService.createPatient(patientDto);
        }

        return "addPatient";
    }

    @GetMapping("/list")
    public String getShotListPatients(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        page.ifPresent(p -> currentPage = p);
        size.ifPresent(s -> pageSize = s);

        Page<PatientDto> patientPage = patientService.
                paginatedList(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("patientPage", patientPage);

        int totalPages = patientPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "list";
    }

    @GetMapping("/get/{id}")
    public ModelAndView getListOfPatient(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("viewPatientInfo");


        List<StudyDto> studies = patientService.getListStudiesOfPatient(id);
        mav.addObject("studies", studies);

        return mav;
    }
}
