package com.aws.ec2java.domain.ports.genre;

import java.util.List;
import java.util.Optional;

import com.aws.ec2java.domain.models.Genre;

public interface GenresUseCases {
    Genre addGenre(String genreName);
    Optional<Genre> getGenre(String genre);
    void deleteGenre(String genre);
    List<Genre> getAllGenres();
}
