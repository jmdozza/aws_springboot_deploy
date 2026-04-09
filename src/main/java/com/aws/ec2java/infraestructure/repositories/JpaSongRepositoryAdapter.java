package com.aws.ec2java.infraestructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aws.ec2java.domain.models.Song;
import com.aws.ec2java.domain.ports.song.SongRepositoryPort;
import com.aws.ec2java.infraestructure.entitys.AlbumEntity;
import com.aws.ec2java.infraestructure.entitys.GenreEntity;
import com.aws.ec2java.infraestructure.entitys.SongEntity;
import com.aws.ec2java.infraestructure.mappers.SongMapper;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class JpaSongRepositoryAdapter implements SongRepositoryPort {

    private final JpaSongRespository jpaSongRespository;
    private final SongMapper songMapper;

    @Override
    @Transactional
    public Song addSong(Song newSong) {
        return songMapper.toDomain(jpaSongRespository.save(songMapper.toEntity(newSong)));
    }

    @Override
    public List<Song> getAllSongs() {
        return jpaSongRespository.findAll().stream()
                                            .map(songMapper::toDomain)
                                            .toList();
    }

    @Override
    public Optional<Song> getSongById(UUID songId) {
        return jpaSongRespository.findById(songId).map(songMapper::toDomain);
    }
}
