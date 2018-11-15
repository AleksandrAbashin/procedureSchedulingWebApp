package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.dto.StudyDto;
import com.firstline.procedure.scheduling.parser.ExcelPOIHelper;
import com.firstline.procedure.scheduling.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class PatientController {

    private static int currentPage = 1;
    private static int pageSize = 5;
    private String fileLocation;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PatientService patientService;

    @Autowired
    ExcelPOIHelper excelPOIHelper;

    @GetMapping("/patient")
    public String editorPage(Model model) {
        model.addAttribute("patientDto", new PatientDto());
        return "addPatient";
    }


    @PostMapping("/patient")
    public String createPatient(@RequestParam("file") MultipartFile file,
                                @ModelAttribute @Valid PatientDto patientDto,
                                BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addPatient";
        } else {
            patientService.createPatient(patientDto);
        }

        if(file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        }

        String uuidFile = UUID.randomUUID().toString();
        fileLocation = uuidFile + "." + file.getOriginalFilename();

        file.transferTo(new File(uploadPath + "/" + fileLocation));

        return "addPatient";
    }



    @RequestMapping(method = RequestMethod.GET, value = "/readPOI")
    public String readPOI(Model model) throws IOException {

        if (fileLocation != null) {
            if (fileLocation.endsWith(".xlsx") || fileLocation.endsWith(".xls")) {
                Map<Integer, List<String>> data
                        = excelPOIHelper.readExcel(fileLocation);
                model.addAttribute("data", data);
            } else {
                model.addAttribute("message", "Not a valid excel file!");
            }
        } else {
            model.addAttribute("message", "File missing! Please upload an excel file.");
        }
        return "excel";

    }

    @GetMapping/*("/patient/list")*/
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

    @GetMapping("/patient/get/{id}")
    public ModelAndView getListOfPatientStudies(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("viewPatientInfo");

        List<StudyDto> studies = patientService.getListStudiesOfPatient(id);
        mav.addObject("studies", studies);

        return mav;
    }
}
