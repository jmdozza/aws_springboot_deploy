package com.aws.ec2java.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.aws.ec2java.application.ArtistService;
import com.aws.ec2java.domain.ports.ArtistRepositoryPort;

@Component
public class ApplicationConfig {
    @Bean
    public ArtistService artistService(ArtistRepositoryPort artPort){
        return new ArtistService(artPort);
    }
}
