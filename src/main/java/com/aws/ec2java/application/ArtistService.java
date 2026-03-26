package com.aws.ec2java.application;

import com.aws.ec2java.domain.models.Artist;
import com.aws.ec2java.domain.ports.ArtistRepositoryPort;
import com.aws.ec2java.domain.ports.CreateArtistUseCase;

public class ArtistService implements CreateArtistUseCase{
    private final ArtistRepositoryPort artistRepo;

    public ArtistService(ArtistRepositoryPort artRepo){
        this.artistRepo=artRepo;
    }

    @Override
    public Artist createArtist(Artist newAartist) {
        return artistRepo.insertArtist(newAartist);
    }
    
}
