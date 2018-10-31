package com.firstline.procedure.scheduling.repos;

import com.firstline.procedure.scheduling.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {
    Study getById(Long id);
}
