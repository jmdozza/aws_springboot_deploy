package com.aws.ec2java.domain.ports.genre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.aws.ec2java.domain.models.Album;
import com.aws.ec2java.domain.models.Genre;

public interface GenreRepositoryPort {
    Genre insertGenre(Genre newGenre);
    Optional<Genre> getGenre(Genre newGenre);
    Optional<Genre> getGenreById(UUID genreId);
    List<Genre> getGeneresByIds(List<UUID> genresIds);
    void deleteGenere(String name);
    List<Genre> getAllGenres();
}
