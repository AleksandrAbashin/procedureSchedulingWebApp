package com.firstline.service.impl;

import com.firstline.dto.StudyDto;
import com.firstline.mapper.StudyMapper;
import com.firstline.repos.StudyRepository;
import com.firstline.service.ElasticService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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
                response = client.prepareIndex("s", "studies", studyDto.getId().toString())
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
                .get();

        return response.toString();
    }

    @Override
    public String analyzed(String index, String nameField) {
        AnalyzeRequest request = new AnalyzeRequest(index);

        request.field("description")
                .analyzer("simple")

                .text(nameField);


        try {
            AnalyzeResponse response = client.admin().indices().analyze(request).actionGet();
            List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
            List<String> list = new ArrayList<>();

            for (AnalyzeResponse.AnalyzeToken token : tokens) {
                list.add(token.getType());
            }
            return list.toString();

        } catch (NullPointerException e) {
            return "null";
        }
    }

    @Override
    public JSONObject searchByAnalazer(String index, String nameField) throws Exception {

        XContentBuilder builder = jsonBuilder()
                .startObject()
                .field("description", nameField)
                .field("tokenizer", "keyword")
                .field("filter", "lowercase")
                .endObject();

        String subString = new StringBuffer(nameField).substring(nameField.length() - 1);

        String wild = "{\"" +
                "match\":{" +
                "\"wildcard\" :" + "{ \"description\":" + "\"" + "*" + subString + "\" }" +
                " }" +
                "}";

        String q = "{\"" +
                "match_phrase_prefix\":{" +
                "\"description\" : {" +
                "\"query\" :" + "\"" + nameField + "\"," +
                "\"max_expansions\" : 10," +
                "\"analyzer\" : \"standard\"}" +
                " }" +
                "}";

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        searchRequestBuilder.setQuery(QueryBuilders
               // .wrapperQuery(q)
                .wildcardQuery("description", "*" + subString + "*")
        );

        SearchResponse response = searchRequestBuilder.execute().actionGet();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.toString());

        return json;
    }


    @Override
    public String searchSecond(String index, String nameField) {
        try {
            setAnalyzer(index);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return client.prepareSearch(index)
                .setQuery(
                        QueryBuilders.queryStringQuery("description:" + "\"" + nameField + "\"")
                                .analyzer("my_analyzer")
                        //   .analyzer(analyzer)

                )
                .execute()
                .actionGet().toString();
    }

    public void setAnalyzer(String index) throws IOException {

        client.admin().indices().prepareCreate(index)
                .setSettings(Settings.builder().loadFromSource(
                        jsonBuilder()
                                .startObject()
                                .startObject("analysis")
                                .startObject("analyzer")
                                .startObject("my_analyzer")
                                .field("type", "custom")
                                .field("tokenizer", "ngram")
                                .field("filter", new String[]{"standard", "lowercase"})
                                .endObject()
                                .endObject()
                                .endObject()
                                .endObject().string(), XContentType.JSON))
                .execute().actionGet();
    }
}
