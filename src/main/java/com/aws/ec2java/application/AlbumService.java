package com.aws.ec2java.application;

import java.util.List;
import java.util.Optional;

import com.aws.ec2java.application.command.CreateAlbumCommand;
import com.aws.ec2java.domain.models.Album;
import com.aws.ec2java.domain.models.Artist;
import com.aws.ec2java.domain.models.Genre;
import com.aws.ec2java.domain.ports.album.AlbumRepositoryPort;
import com.aws.ec2java.domain.ports.album.AlbumUseCases;
import com.aws.ec2java.domain.ports.artist.ArtistRepositoryPort;
import com.aws.ec2java.domain.ports.genre.GenreRepositoryPort;

import jakarta.persistence.EntityNotFoundException;

public class AlbumService implements AlbumUseCases{
    private final AlbumRepositoryPort albumRepositoryPort;
    private final ArtistRepositoryPort artistRepositoryPort;
    private final GenreRepositoryPort genreRepositoryPort;

    public AlbumService(AlbumRepositoryPort albumRepositoryPort,
        ArtistRepositoryPort artistRepositoryPort,
        GenreRepositoryPort genreRepositoryPort
    ){
        this.albumRepositoryPort=albumRepositoryPort;
        this.artistRepositoryPort=artistRepositoryPort;
        this.genreRepositoryPort= genreRepositoryPort;
    }

    @Override
    public Album addAlbum(CreateAlbumCommand newAlbum) {
        //Debemos asegurarnos de que el uuid del genero y el artista existan
        Artist obtainedArtist =artistRepositoryPort.getArtistById(newAlbum.artist_id()).orElseThrow(()->new EntityNotFoundException("ARTIST NOT FOUND"));
        Genre obtainedGenre = genreRepositoryPort.getGenreById(newAlbum.genre_id()).orElseThrow(()-> new EntityNotFoundException("GENRE NOT FOUND"));
        Album createdAlbum = Album.builder()
                        .artist(obtainedArtist)
                        .genre(obtainedGenre)
                        .release_date(newAlbum.release_date())
                        .title(newAlbum.title())
                        .build();
        return albumRepositoryPort.insertAlbum(createdAlbum);
    }

    @Override
    public void deleteAlbum(Album album) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAlbum'");
    }

    @Override
    public Optional<Album> getAlbum(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAlbum'");
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepositoryPort.getAllAlbums();
    }
    
}
