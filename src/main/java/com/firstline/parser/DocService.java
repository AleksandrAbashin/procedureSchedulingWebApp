package com.firstline.parser;

import java.io.IOException;
import java.util.List;

public interface DocService {
    public List<String> readDoc(String filename) throws IOException;
}
