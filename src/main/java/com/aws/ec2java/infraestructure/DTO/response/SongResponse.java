package com.aws.ec2java.infraestructure.DTO.response;

import java.util.List;
import java.util.UUID;

public record SongResponse(
    UUID id,
    String title,
    String audioKey,
    AlbumResponse album,
    List<GenreResponse> genres
) {
    
} 
