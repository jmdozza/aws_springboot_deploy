package com.aws.ec2java.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aws.ec2java.application.command.CreateAlbumCommand;
import com.aws.ec2java.domain.models.Album;
import com.aws.ec2java.infraestructure.DTO.request.AlbumRequest;
import com.aws.ec2java.infraestructure.DTO.response.AlbumResponse;
import com.aws.ec2java.infraestructure.entitys.AlbumEntity;

@Mapper(componentModel = "spring",
    uses={ArtistMapper.class, GenreMapper.class}
)
public interface AlbumMapper {
    CreateAlbumCommand toCommand(AlbumRequest albRequest);
    @Mapping(target = "releaseDate", source = "release_date")
    @Mapping(target = "genreAlb", source = "genre")
    @Mapping(target = "songs", ignore = true)
    AlbumEntity toEntity(Album newAlbum);
    
    @Mapping(target = "release_date", source = "releaseDate")
    @Mapping(target = "genre", source = "genreAlb")
    Album toDomain(AlbumEntity albumEntity);

    @Mapping(target = "releaseDate", source = "release_date")
    AlbumResponse toResponse(Album album);
    
}
