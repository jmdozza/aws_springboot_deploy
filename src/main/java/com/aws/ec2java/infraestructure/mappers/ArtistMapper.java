package com.aws.ec2java.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aws.ec2java.domain.models.Artist;
import com.aws.ec2java.infraestructure.DTO.request.ArtistRequest;
import com.aws.ec2java.infraestructure.DTO.response.ArtistResponse;
import com.aws.ec2java.infraestructure.entitys.ArtistEntity;

@Mapper(componentModel="spring")
public interface ArtistMapper {

    @Mapping(source = "artistic_name",target = "artisticname")
    @Mapping(target = "albums",ignore = true)
    @Mapping(target = "groups",ignore = true)
    @Mapping(target = "members",ignore = true)
    ArtistEntity toEntity(Artist artist);

    @Mapping(target = "uuid",ignore = true)
    @Mapping(target = "artistic_name",source= "artisticname")
    Artist toDomain(ArtistRequest artist);

    @Mapping(target = "id",source = "uuid")
    ArtistResponse toResponse(Artist artist);
    
    @Mapping(target = "artistic_name",source = "artisticname")
    Artist toDomain(ArtistEntity artEntity);
}
