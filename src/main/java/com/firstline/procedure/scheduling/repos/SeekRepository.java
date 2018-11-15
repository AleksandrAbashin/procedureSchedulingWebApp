package com.firstline.procedure.scheduling.repos;

import com.firstline.procedure.scheduling.domain.Seek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface SeekRepository extends JpaRepository<Seek, Long> {
}
