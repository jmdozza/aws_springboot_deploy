package com.aws.ec2java.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aws.ec2java.domain.models.Genre;
import com.aws.ec2java.infraestructure.DTO.response.GenreResponse;
import com.aws.ec2java.infraestructure.entitys.GenreEntity;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Mapping(source = "id", target = "uuid")
    GenreResponse toResponse(Genre genre);
    Genre toDomain(GenreEntity genrEntity);
    @Mapping(target = "songs", ignore = true)
    @Mapping(target = "albums", ignore = true)
    GenreEntity toEntity (Genre genre);
}
