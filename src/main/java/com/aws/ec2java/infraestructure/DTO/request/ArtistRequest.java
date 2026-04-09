package com.aws.ec2java.infraestructure.DTO.request;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;

public record ArtistRequest(
    @NotBlank
    @JsonAlias({"artistic_name", "artisticName"})
    String artisticname,

    Boolean isGroup


) 
{
} 
