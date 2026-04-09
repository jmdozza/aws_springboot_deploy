package com.aws.ec2java.infraestructure.DTO.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record SongRequest(
    @NotBlank String title,
    @NotNull UUID album_id,
    @NotEmpty List<@NotNull UUID> genre_ids
) {
    
}
