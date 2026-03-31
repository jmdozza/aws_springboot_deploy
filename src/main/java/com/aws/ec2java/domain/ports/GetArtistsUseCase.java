package com.aws.ec2java.domain.ports;

import java.util.Optional;

import com.aws.ec2java.domain.models.Artist;

public interface GetArtistsUseCase {
    Optional<Artist> getArtist(String name);
}
