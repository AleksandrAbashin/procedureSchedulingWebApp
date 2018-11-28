package com.firstline.repos;

import com.firstline.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudyRepository extends JpaRepository<Study, Long> {

   /* @Query(value = "SELECT s FROM Study s INNER JOIN s WHERE s.id = :id")
    List<Study> getStudyByPatientId(@Param("id") Long id);*/

    Study getById(Long id);
}
