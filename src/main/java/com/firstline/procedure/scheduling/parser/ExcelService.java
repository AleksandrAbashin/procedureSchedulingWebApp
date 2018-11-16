package com.firstline.procedure.scheduling.parser;

import com.firstline.procedure.scheduling.domain.PatientInfo;

import java.io.IOException;
import java.util.List;

public interface ExcelService {
    public List<PatientInfo> readExcel(PatientInfo patientInfo) throws IOException;
}
