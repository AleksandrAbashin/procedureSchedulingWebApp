package com.firstline.service.impl;

import com.firstline.dto.StudyDto;
import com.firstline.mapper.StudyMapper;
import com.firstline.repos.StudyRepository;
import com.firstline.service.ElasticService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Service
public class ElasticServiceImp implements ElasticService {

    private final TransportClient client;

    private final StudyMapper studyMapper;

    private final StudyRepository studyRepository;

    @Autowired
    public ElasticServiceImp(TransportClient client, StudyMapper studyMapper, StudyRepository studyRepository) {
        this.client = client;
        this.studyMapper = studyMapper;
        this.studyRepository = studyRepository;
    }

    @Override
    @Scheduled(cron = "*/45 * * * * ?")
    public void indexStudy() {
        List<StudyDto> studies = studyMapper.fromListStudy(studyRepository.findAll());


        for (StudyDto studyDto : studies) {
            IndexResponse response = null;
            try {
                response = client.prepareIndex("study", "studies", studyDto.getId().toString())
                        .setSource(jsonBuilder()
                                .startObject()
                                .field("description", studyDto.getDescription())
                                .field("status", studyDto.getStatus())
                                .field("plannedStartTime", studyDto.getPlannedStartTime())
                                .field("estimatedEndTime", studyDto.getEstimatedEndTime())
                                .field("patientId", studyDto.getPatientId())
                                .endObject()
                        )
                        .get();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(response.getResult().toString());
        }
    }

    @Override
    public String search(String index, String nameField) {
        SearchResponse response = client.prepareSearch(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("description", nameField))
              //  .setFilter(FilterBuilders.rangeFilter("age").from(12).to(18))
                //    .setPostFilter(QueryBuilders.rangeQuery("id").from(1).to(3))     // Filter
                //   .setFrom(0).setSize(20)
                .get();

        return response.toString();
    }
}
