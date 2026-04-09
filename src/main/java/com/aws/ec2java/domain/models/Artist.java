package com.aws.ec2java.domain.models;

import java.util.UUID;

public record Artist(
    UUID uuid,
    String artistic_name,
    Boolean isGroup
) {
}
