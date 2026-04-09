package com.aws.ec2java.domain.models;

import lombok.Builder;

@Builder
public record SongFile(
    
    byte [] audioFileBytes,
    String audioFileName,
    String contentType
)
{}
