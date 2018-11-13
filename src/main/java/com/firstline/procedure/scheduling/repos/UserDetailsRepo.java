package com.firstline.procedure.scheduling.repos;

import com.firstline.procedure.scheduling.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String > {
}
