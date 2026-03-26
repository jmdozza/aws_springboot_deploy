package com.aws.ec2java.infraestructure.repositories;

import org.springframework.stereotype.Component;

import com.aws.ec2java.domain.models.Artist;
import com.aws.ec2java.domain.ports.ArtistRepositoryPort;
import com.aws.ec2java.infraestructure.mappers.ArtistMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JpaArtistRepositoryAdapter implements ArtistRepositoryPort {

    private final JpaArtistRepository jpaArtistRepository;
    private final ArtistMapper artistMapper;
    
    @Override
    public Artist insertArtist(Artist newArtist) {
        return artistMapper.toDomain(jpaArtistRepository.save(artistMapper.toEntity(newArtist)));
    }
    
}
