import com.firstline.procedure.scheduling.configs.DataBaseConfig;
import com.firstline.procedure.scheduling.configs.MyWebAppInitializer;
import com.firstline.procedure.scheduling.configs.TestConfig;
import com.firstline.procedure.scheduling.configs.WebMvcConfiguration;
import com.firstline.procedure.scheduling.domain.Patient;
import com.firstline.procedure.scheduling.domain.enums.Sex;
import com.firstline.procedure.scheduling.repos.PatientRepository;
import com.firstline.procedure.scheduling.service.PatientService;
import com.firstline.procedure.scheduling.service.impl.PatientServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {DataBaseConfig.class,
        MyWebAppInitializer.class,
        WebMvcConfiguration.class,
        TestConfig.class})
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    Pageable pageable;

    @InjectMocks
    PatientService patientService = new PatientServiceImp();


    @Test
    public void getPatientByIdTest() {
        Patient patientDto = new Patient();
        patientDto.setId(1001L);
        patientDto.setPatientName("Aleks");
        patientDto.setPatientSex(Sex.MALE);

        Mockito.when(patientRepository.getById(1001L)).thenReturn(patientDto);
    }

    @Test
    public void paginatedListTest() {

        Mockito.when(null).thenReturn(null);
    }

}
