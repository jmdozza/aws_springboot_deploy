package com.aws.ec2java.infraestructure.DTO.response;

import java.time.LocalDate;
import java.util.UUID;

public record AlbumResponse(
    UUID id,
    String title,
    LocalDate releaseDate,
    GenreResponse genre,
    ArtistResponse artist
) {
    
}
