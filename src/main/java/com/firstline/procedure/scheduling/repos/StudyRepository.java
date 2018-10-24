package com.firstline.procedure.scheduling.repos;

import com.firstline.procedure.scheduling.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
