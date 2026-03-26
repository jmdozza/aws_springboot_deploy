package com.aws.ec2java.domain.models;

import java.util.UUID;

public record Song(
    UUID uuid,
    String name,
    String compositor,
    Artist artist
) {
}
