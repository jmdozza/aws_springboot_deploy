package com.aws.ec2java.domain.models;

import java.util.List;
import java.util.UUID;

import lombok.Builder;

@Builder
public record Song(
    UUID uuid,
    String title,
    Integer duration,
    String audio_url,
    List<Genre> genres,
    Album album
) {
}
