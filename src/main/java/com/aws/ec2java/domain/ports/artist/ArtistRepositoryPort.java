package com.aws.ec2java.domain.ports.artist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.aws.ec2java.domain.models.Artist;

public interface ArtistRepositoryPort {
    Artist insertArtist(Artist newArtist);
    List<Artist> getAllArtists();
    Optional<Artist> getArtist(String name);
    Optional <Artist> getArtistById(UUID id);
}
