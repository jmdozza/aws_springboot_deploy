package com.aws.ec2java.infraestructure.DTO.request;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AlbumRequest(
    @NotBlank String title,
    @NotNull LocalDate release_date,
    @NotNull UUID artist_id,
    @NotNull UUID genre_id
) {
}
