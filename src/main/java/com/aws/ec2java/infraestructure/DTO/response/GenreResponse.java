package com.aws.ec2java.infraestructure.DTO.response;

import java.util.UUID;

public record GenreResponse(
    UUID uuid,
    String name
)
{    }
