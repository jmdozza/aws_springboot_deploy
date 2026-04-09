package com.aws.ec2java.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.aws.ec2java.application.AlbumService;
import com.aws.ec2java.application.ArtistService;
import com.aws.ec2java.application.GenreService;
import com.aws.ec2java.application.SongService;
import com.aws.ec2java.domain.ports.album.AlbumRepositoryPort;
import com.aws.ec2java.domain.ports.artist.ArtistRepositoryPort;
import com.aws.ec2java.domain.ports.genre.GenreRepositoryPort;
import com.aws.ec2java.domain.ports.out.FileStoragePort;
import com.aws.ec2java.domain.ports.song.SongRepositoryPort;

@Component
public class ApplicationConfig {
    @Bean
    public ArtistService artistService(ArtistRepositoryPort artPort){
        return new ArtistService(artPort);
    }

    @Bean
    public GenreService genreService(GenreRepositoryPort genRepPort){
        return new GenreService(genRepPort);
    }

    @Bean
    public AlbumService albumService(AlbumRepositoryPort albumRepositoryPort,
                                    ArtistRepositoryPort artistRepositoryPort,
                                    GenreRepositoryPort genreRepositoryPort
    ){
        return new AlbumService(albumRepositoryPort, artistRepositoryPort, genreRepositoryPort);
    }

    @Bean
    public SongService songService(
            SongRepositoryPort songRepositoryPort,
            AlbumRepositoryPort albumRepositoryPort,
            GenreRepositoryPort genreRepositoryPort,
            FileStoragePort fileStoragePort
    ) {
        return new SongService(songRepositoryPort, albumRepositoryPort, genreRepositoryPort, fileStoragePort);
    }
    
}
