package com.aws.ec2java.domain.ports.artist;

import java.util.List;
import java.util.Optional;

import com.aws.ec2java.domain.models.Artist;

public interface ArtistUseCases {
    Artist createArtist(Artist newAartist);
    List<Artist> getAllArtists();
    Optional<Artist> getArtist(String name);
}
