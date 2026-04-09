package com.aws.ec2java.infraestructure.DTO.response;

public record SongFileResponse(
    byte[] songBytes,   
    String objKey
) {
}
