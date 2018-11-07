import com.firstline.procedure.scheduling.configs.DataBaseConfig;
import com.firstline.procedure.scheduling.configs.WebMvcConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataBaseConfig.class,
        WebMvcConfiguration.class})
@WebAppConfiguration
public class PatientServiceTest extends Assert {

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
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("patientPage", "pageNumbers"));

    }

    @Test
    public void getListOfPatientStudiesTest() throws Exception {
      /*  List<StudyDto> studies = Arrays.asList(
                new StudyDto(1L, "Gripp", "PLANNED", 1L),
                new StudyDto(2L, "OSP", "PLANNED", 1L));
        patientService.createPatient(new PatientDto(1L, "Aleks", "MALE", studies));
        when(patientService.getListStudiesOfPatient(1L)).thenReturn(studies);*/

        mockMvc.perform(get("/patient/get/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html"))
                .andExpect(view().name("viewPatientInfo"))
                .andExpect(model().attributeExists("studies"));
    }


 /*   @Test
 //   public Page<PatientDto> pagination (Pageable pageable) {
    public void paginationTest () {
        patientRepository.save(new Patient("Misha",Sex.MALE, 1L));
        List<PatientDto> patients = patientMapper.fromListPatient(patientRepository.findAll());

        int pageSize = 1;
        int currentPage = 1;

        Page<PatientDto> patientPage
                = new PageImpl<PatientDto>(patients, PageRequest.of(1, 1), patients.size());


        Assert.assertEquals(patientService.paginatedList(new PageRequest.of(1,1)),patientPage);

    }*/

}
