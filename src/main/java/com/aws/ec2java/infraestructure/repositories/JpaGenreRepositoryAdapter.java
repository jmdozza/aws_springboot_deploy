package com.aws.ec2java.infraestructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.aws.ec2java.domain.models.Genre;
import com.aws.ec2java.domain.ports.genre.GenreRepositoryPort;
import com.aws.ec2java.infraestructure.entitys.GenreEntity;
import com.aws.ec2java.infraestructure.mappers.GenreMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JpaGenreRepositoryAdapter implements GenreRepositoryPort {

    private final JpaGenreRepository jpaGenreRepository;
    private final GenreMapper genreMapper;

    @Override
    public Genre insertGenre(Genre newGenre) {
        GenreEntity genreEntity = GenreEntity.builder()
                .id(newGenre.id())
                .name(newGenre.name())
                .build();

        GenreEntity savedGenre = jpaGenreRepository.save(genreEntity);
        return genreMapper.toDomain(savedGenre);
    }

    @Override
    public Optional<Genre> getGenre(Genre newGenre) {
        throw new UnsupportedOperationException("Unimplemented method 'getGenre'");
    }

    @Override
    public void deleteGenere(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteGenere'");
    }

    @Override
    public List<Genre> getAllGenres() {
        List<GenreEntity> genres = jpaGenreRepository.findAll();
        return genres.stream().map(genreMapper::toDomain).toList();
    }

    @Override
    public Optional<Genre> getGenreById(UUID genreId) {
        return jpaGenreRepository.findById(genreId).map(genreMapper::toDomain);
    }

    @Override
    public List<Genre> getGeneresByIds(List<UUID> genresIds) {
        // TODO Auto-generated method stub
        return jpaGenreRepository.findAllById(genresIds).
                stream()
                .map(genreMapper::toDomain)
                .toList();
    }

    
}
