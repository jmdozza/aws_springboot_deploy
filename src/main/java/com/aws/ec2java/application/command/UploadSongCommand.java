package com.aws.ec2java.application.command;

import java.util.List;
import java.util.UUID;

import lombok.Builder;

@Builder
public record UploadSongCommand(
    String title,
    UUID album_id,
    List<UUID> genre_ids,
    
    //Datos del archivo
    byte [] audioFileBytes,
    String originalFileName,
    String contentType
)
{}
