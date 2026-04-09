package com.aws.ec2java.infraestructure.DTO.response;

import lombok.Builder;

@Builder
public record SongFullResponse(
    SongResponse song,
    SongFileResponse songFile
) {
    
}
