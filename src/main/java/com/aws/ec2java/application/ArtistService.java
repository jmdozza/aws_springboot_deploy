package com.aws.ec2java.application;

import java.util.List;
import java.util.Optional;

import com.aws.ec2java.domain.models.Artist;
import com.aws.ec2java.domain.ports.ArtistRepositoryPort;
import com.aws.ec2java.domain.ports.CreateArtistUseCase;
import com.aws.ec2java.domain.ports.GetAllArtistsUseCase;
import com.aws.ec2java.domain.ports.GetArtistsUseCase;

public class ArtistService implements CreateArtistUseCase, GetAllArtistsUseCase, GetArtistsUseCase{
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
