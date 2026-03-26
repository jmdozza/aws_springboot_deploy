package com.aws.ec2java.domain.ports;

import com.aws.ec2java.domain.models.Artist;

public interface ArtistRepositoryPort {
    Artist insertArtist(Artist newArtist);
    
}
