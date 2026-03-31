package com.aws.ec2java.application;

import java.util.List;

import com.aws.ec2java.domain.models.Artist;
import com.aws.ec2java.domain.ports.ArtistRepositoryPort;
import com.aws.ec2java.domain.ports.CreateArtistUseCase;
import com.aws.ec2java.domain.ports.GetAllArtistsUseCase;

public class ArtistService implements CreateArtistUseCase, GetAllArtistsUseCase{
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
    
}
