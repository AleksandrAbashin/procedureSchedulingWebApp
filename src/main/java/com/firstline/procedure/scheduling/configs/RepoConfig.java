package com.firstline.procedure.scheduling.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@RequiredArgsConstructor
@EnableJpaRepositories({"com.firstline.procedure.scheduling.repos"})
public class RepoConfig  {

}
