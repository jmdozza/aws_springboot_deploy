package com.aws.ec2java.infraestructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.aws.ec2java.domain.models.Artist;
import com.aws.ec2java.domain.ports.artist.ArtistRepositoryPort;
import com.aws.ec2java.infraestructure.entitys.ArtistEntity;
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

    @Override
    public List<Artist> getAllArtists() {
        List<ArtistEntity> artists= jpaArtistRepository.findAll();
        return  artists.stream().map(artistMapper::toDomain).toList();
    }

    @Override
    public Optional<Artist> getArtist(String name) {
        return jpaArtistRepository.findByArtisticnameContaining(name).map(artistMapper::toDomain);
    }

    @Override
    public Optional<Artist> getArtistById(UUID id) {
        return jpaArtistRepository.findById(id).map(artistMapper::toDomain);
    }
    
}
