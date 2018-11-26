package com.firstline.parser;

import com.firstline.domain.PatientInfo;

import java.io.IOException;
import java.util.List;

public interface ExcelService {
    public List<PatientInfo> readExcel(PatientInfo patientInfo) throws IOException;
}
