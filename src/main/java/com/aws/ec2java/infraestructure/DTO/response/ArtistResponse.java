package com.aws.ec2java.infraestructure.DTO.response;

import java.util.UUID;

public record ArtistResponse(
    UUID id,
    String artistic_name,
    Boolean isGroup
) {
    
}
