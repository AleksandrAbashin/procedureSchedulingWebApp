package com.firstline.procedureScheduling.repos;

import com.firstline.procedureScheduling.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
