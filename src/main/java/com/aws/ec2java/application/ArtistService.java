package com.aws.ec2java.application;

import java.util.List;
import java.util.Optional;

import com.aws.ec2java.domain.models.Artist;
import com.aws.ec2java.domain.ports.artist.ArtistRepositoryPort;
import com.aws.ec2java.domain.ports.artist.ArtistUseCases;


public class ArtistService implements ArtistUseCases{
    private final ArtistRepositoryPort artistRepo;

    public ArtistService(ArtistRepositoryPort artRepo){
        this.artistRepo=artRepo;
    }

    @Override
    public Artist createArtist(Artist newAartist) {
        return artistRepo.insertArtist(newAartist);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepo.getAllArtists();
    }

    @Override
    public Optional<Artist> getArtist(String name) {
        Optional<Artist> optArtist=artistRepo.getArtist(name);
        Artist obtainedArt= optArtist.orElseThrow(()-> new RuntimeException ("ARTIST NOT FOUND"));
        return optArtist;
    }


}
