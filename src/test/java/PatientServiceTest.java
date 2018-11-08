import com.firstline.procedure.scheduling.configs.DataBaseConfig;
import com.firstline.procedure.scheduling.configs.WebMvcConfiguration;
import com.firstline.procedure.scheduling.domain.Patient;
import com.firstline.procedure.scheduling.domain.enums.Sex;
import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.mapper.PatientMapper;
import com.firstline.procedure.scheduling.mapper.StudyMapper;
import com.firstline.procedure.scheduling.repos.PatientRepository;
import com.firstline.procedure.scheduling.repos.StudyRepository;
import com.firstline.procedure.scheduling.service.PatientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataBaseConfig.class,
        WebMvcConfiguration.class})
@WebAppConfiguration
public class PatientServiceTest extends Assert {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientService patientService;
    @Autowired
    PatientMapper patientMapper;
    @Autowired
    StudyMapper studyMapper;
    @Autowired
    StudyRepository studyRepository;
    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void getShotListPatientsTest() throws Exception {
        mockMvc.perform(get("/patient/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"));
    }

    @Test
    public void getListOfPatientStudiesTest() throws Exception {
        mockMvc.perform(get("/patient/get/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("viewPatientInfo"))
                .andExpect(model().attributeExists("studies"));
    }

    @Test
    public void createPatientTest() throws Exception {
        mockMvc.perform(post("/patient")
                .param("patientName", "Aleks")
                .param("patientSex", "MALE"))
                .andExpect(status().isOk())
                .andExpect(view().name("addPatient"));
    }



    @Test
    @Transactional
    @Rollback
    public void paginatedListTest() {
        int currentPage = 1;
        int pageSize = 1;
        Patient patient = new Patient();
       // patient.setId(1L);
        patient.setPatientName("Misha");
        patient.setPatientSex(Sex.MALE);
        patientService.createPatient(patientMapper.fromPatient(patient));
        List<PatientDto> patients = patientMapper.fromListPatient(patientRepository.findAll());

        Page<PatientDto> patientPageExpect
                = new PageImpl<>(patients, PageRequest.of(currentPage, pageSize), patients.size());

        Page<PatientDto> patientPageActual = patientService.
                paginatedList(PageRequest.of(currentPage - 1, pageSize));


        Assert.assertEquals(patientPageExpect.getContent().get(0).getPatientName()
                , patientPageActual.getContent().get(0).getPatientName());
    }
}
    /*@Test
    @Transactional
    public void getListStudiesOfPatientTest() {
        Patient patient = new Patient();
        patient.setPatientName("Misha");
        patient.setPatientSex(Sex.MALE);
        Study study = new Study();
        study.setDescription("Gripp");
        study.setStatus(Status.FINISHED);
        study.setPlannedStartTime(LocalDate.parse("10-10-2018"));
        List<Study> studies = Arrays.asList(study);
        patient.setStudies(studies);
        patientService.createPatient(patientMapper.fromPatient(patient));

        List<StudyDto> dtoList = studyMapper.fromListStudy(patient.getStudies());
        List<StudyDto> listActual = patientService.getListStudiesOfPatient(1L);

        Assert.assertEquals(dtoList.get(0).getDescription(), listActual.get(0).getDescription());
    }*/