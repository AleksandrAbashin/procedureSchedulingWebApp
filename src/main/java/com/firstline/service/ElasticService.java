package com.firstline.service;

import net.minidev.json.JSONObject;

public interface ElasticService {

    public void indexStudy();

    public String search(String index, String nameField);

    public String analyzed(String index, String nameField);

    public JSONObject searchByAnalazer(String index, String nameField) throws Exception;

    public String searchSecond(String index, String nameField);

}
