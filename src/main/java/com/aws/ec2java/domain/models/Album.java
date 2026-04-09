package com.aws.ec2java.domain.models;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;

@Builder
public record Album(
    UUID id,
    String title,
    LocalDate release_date,
    Artist artist,
    Genre genre
) {
}
