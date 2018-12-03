package com.firstline.repos;

import com.firstline.document.StudyDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StudyDocRepository extends ElasticsearchRepository<StudyDoc, String> {

    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"description\" : {\"query\" : \"?\",\"analyze_wildcard\" : true}}}}}")
    Page<StudyDoc> findByDescriptionContaining(String name, Pageable pageable);
}
