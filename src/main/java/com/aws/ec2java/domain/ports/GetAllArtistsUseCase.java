package com.aws.ec2java.domain.ports;

import java.util.List;

import com.aws.ec2java.domain.models.Artist;

public interface GetAllArtistsUseCase {
    List<Artist> getAllArtists();
}
