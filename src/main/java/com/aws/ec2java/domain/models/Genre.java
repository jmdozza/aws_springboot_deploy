package com.aws.ec2java.domain.models;

import java.util.UUID;

import lombok.Builder;

@Builder
public record Genre(
    UUID id,
    String name
) {
}
