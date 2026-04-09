package com.aws.ec2java.infraestructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.aws.ec2java.domain.models.Album;
import com.aws.ec2java.domain.ports.album.AlbumRepositoryPort;
import com.aws.ec2java.infraestructure.mappers.AlbumMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JpaAlbumRepositoryAdapter implements AlbumRepositoryPort{

    private final JpaAlbumRepository jpaAlbumRepository;
    private final AlbumMapper albumMapper;

    @Override
    public Album insertAlbum(Album newAlbum) {
        return albumMapper.toDomain(jpaAlbumRepository.save(albumMapper.toEntity(newAlbum)));
    }

    @Override
    public Optional<Album> getAlbumById(UUID albumId) {
        return jpaAlbumRepository.findById(albumId).map(albumMapper::toDomain);
    }

    @Override
    public List<Album> getAllAlbums() {
        return jpaAlbumRepository.findAll().stream().map(albumMapper::toDomain).toList();
    }

    
}
