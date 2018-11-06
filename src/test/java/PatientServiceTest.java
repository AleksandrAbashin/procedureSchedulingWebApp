import com.firstline.procedure.scheduling.configs.DataBaseConfig;
import com.firstline.procedure.scheduling.configs.MyWebAppInitializer;
import com.firstline.procedure.scheduling.configs.TestConfig;
import com.firstline.procedure.scheduling.configs.WebMvcConfiguration;
import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.mapper.PatientMapper;
import com.firstline.procedure.scheduling.repos.PatientRepository;
import com.firstline.procedure.scheduling.service.PatientService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {DataBaseConfig.class,
        MyWebAppInitializer.class,
        WebMvcConfiguration.class,
        TestConfig.class})
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    PatientService patientService;


    @Test
    public void getAllPatients() {

        when(patientService.getAllPatients()).thenReturn(ImmutableList.of());

        List<PatientDto> patientDtoList = patientService.getAllPatients();

        

    }

}
