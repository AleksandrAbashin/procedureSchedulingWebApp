package com.firstline.procedure.scheduling.parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ExcelPOIHelper {
    public Map<Integer, List<String>> readExcel(String fileLocation) throws IOException;
}
