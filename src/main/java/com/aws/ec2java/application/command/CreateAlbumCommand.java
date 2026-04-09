package com.aws.ec2java.application.command;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
public record CreateAlbumCommand(
    String title,
    LocalDate release_date,
    UUID artist_id,
    UUID genre_id
) {
    
}
