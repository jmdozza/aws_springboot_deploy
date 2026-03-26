package com.aws.ec2java.infraestructure.DTO.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ArtistRequest(
    @NotBlank
    String name,

    @NotBlank
    String artisticname,

    @NotNull
    @Positive
    int age
) 
{
} 
