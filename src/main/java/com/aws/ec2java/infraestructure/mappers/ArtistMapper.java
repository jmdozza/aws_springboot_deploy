package com.aws.ec2java.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aws.ec2java.domain.models.Artist;
import com.aws.ec2java.infraestructure.DTO.request.ArtistRequest;
import com.aws.ec2java.infraestructure.DTO.response.ArtistResponse;
import com.aws.ec2java.infraestructure.entitys.ArtistEntity;

@Mapper(componentModel="spring")
public interface ArtistMapper {

    ArtistEntity toEntity(Artist artist);
    Artist toDomain(ArtistRequest artist);
    ArtistResponse toResponse(Artist artist);
    
    @Mapping(target = "uuid", ignore = true)
    Artist toDomain(ArtistEntity artEntity);
}
