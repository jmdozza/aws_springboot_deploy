package com.aws.ec2java.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aws.ec2java.domain.models.SongFile;
import com.aws.ec2java.infraestructure.DTO.response.SongFileResponse;

@Mapper(componentModel = "spring")
public interface SongFileMapper {
    @Mapping(target = "songBytes", source = "audioFileBytes")
    @Mapping(target = "objKey", source="audioFileName")
    SongFileResponse toResponse (SongFile songFile);
    
}
