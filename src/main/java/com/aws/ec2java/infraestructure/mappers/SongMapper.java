package com.aws.ec2java.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aws.ec2java.domain.models.Song;
import com.aws.ec2java.infraestructure.DTO.response.SongResponse;
import com.aws.ec2java.infraestructure.entitys.SongEntity;

@Mapper(componentModel = "spring",
    uses = {AlbumMapper.class,GenreMapper.class}
)
public interface SongMapper {

    @Mapping(target = "audioKey",source = "audio_url")
    @Mapping(target = "id", ignore = true)
    SongEntity toEntity(Song song);

    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "audio_url",source = "audioKey")
    Song toDomain(SongEntity song);
    
    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "audioKey", source = "audio_url")
    SongResponse toResponse(Song song);
}
