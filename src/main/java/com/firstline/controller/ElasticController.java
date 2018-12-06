package com.firstline.controller;

import com.firstline.service.ElasticService;
import com.firstline.service.PatientService;
import net.minidev.json.JSONObject;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@RestController
@RequestMapping("/elastic/")
public class ElasticController {

    private final TransportClient client;

    private PatientService patientService;

    private ElasticService elasticService;

    @Autowired
    public ElasticController(TransportClient client, PatientService patientService, ElasticService elasticService) {
        this.client = client;
        this.patientService = patientService;
        this.elasticService = elasticService;
    }


    @GetMapping("search/{description}")
    public String searchSecond(@PathVariable final String description) {
        return elasticService.searchSecond("s",description);
    }

    @GetMapping("analyzed/{description}")
    public JSONObject analyzedField(@PathVariable final String description) throws Exception {
        return elasticService.searchByAnalazer("s",description);
    }



    @GetMapping("/insert/{id}")
    public String insert(@PathVariable final String id) throws IOException {

        IndexResponse response = client.prepareIndex("patients", "id", id)
                .setSource(jsonBuilder()
                        .startObject()
                        .field("patientName", patientService.getPatientById(1l).getPatientName())
                        .field("patientSex", "MALE")
                    //    .field("patientDateBirth", "")
                        .endObject()
                )
                .get();
        return response.getResult().toString();
    }


    @GetMapping("/view/{id}")
    public Map<String, Object> view(@PathVariable final String id) {
        GetResponse getResponse = client.prepareGet("study", "studies", id).get();
        System.out.println(getResponse.getSource());


        return getResponse.getSource();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable final String id) throws IOException {

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("patients")
                .type("id")
                .id(id)
                .doc(jsonBuilder()
                        .startObject()
                        .field("patientSex", "FEMALE")
                        .endObject());
        try {
            UpdateResponse updateResponse = client.update(updateRequest).get();
            System.out.println(updateResponse.status());
            return updateResponse.status().toString();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
        return "Exception";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable final String id) {

        DeleteResponse deleteResponse = client.prepareDelete("patients", "id", id).get();

        System.out.println(deleteResponse.getResult().toString());
        return deleteResponse.getResult().toString();
    }
}
