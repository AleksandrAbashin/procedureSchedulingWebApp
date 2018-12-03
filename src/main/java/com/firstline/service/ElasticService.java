package com.firstline.service;

public interface ElasticService {

    public void indexStudy();

    public String search(String index, String nameField);

}
