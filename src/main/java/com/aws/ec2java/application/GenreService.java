package com.aws.ec2java.application;

import java.util.List;
import java.util.Optional;

import com.aws.ec2java.domain.models.Genre;
import com.aws.ec2java.domain.ports.genre.GenreRepositoryPort;
import com.aws.ec2java.domain.ports.genre.GenresUseCases;

public class GenreService implements GenresUseCases{
    private final GenreRepositoryPort genreRepositoryPort;

    public GenreService(GenreRepositoryPort genreRepositoryPort){
        this.genreRepositoryPort=genreRepositoryPort;
    }

    @Override
    public Genre addGenre(String newGenre) {
        Genre genre = Genre.builder().name(newGenre).build();
        return genreRepositoryPort.insertGenre(genre);
    }

    //TODO
    @Override
    public Optional<Genre> getGenre(String genre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGenre'");
    }

    //TODO
    @Override
    public void deleteGenre(String genre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteGenre'");
    }


    @Override
    public List<Genre> getAllGenres() {
        return genreRepositoryPort.getAllGenres();
    }
    
}
