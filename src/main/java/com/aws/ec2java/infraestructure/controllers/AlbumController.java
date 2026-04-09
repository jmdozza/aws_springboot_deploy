package com.aws.ec2java.infraestructure.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.ec2java.application.AlbumService;
import com.aws.ec2java.application.command.CreateAlbumCommand;
import com.aws.ec2java.domain.models.Album;
import com.aws.ec2java.infraestructure.DTO.request.AlbumRequest;
import com.aws.ec2java.infraestructure.DTO.response.AlbumResponse;
import com.aws.ec2java.infraestructure.mappers.AlbumMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/v1/album")
@AllArgsConstructor
public class AlbumController {
    private final AlbumService albumService;
    private final AlbumMapper albumMapper;

    @PostMapping
    ResponseEntity<AlbumResponse> createAlbum(@RequestBody @Valid AlbumRequest newAlbum){
        CreateAlbumCommand albumCommand = albumMapper.toCommand(newAlbum);
        Album newAlb = albumService.addAlbum(albumCommand);
        return ResponseEntity.ok(albumMapper.toResponse(newAlb));
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponse>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums().stream().
                                                            map(albumMapper::toResponse)
                                                            .toList()
                                                            );
    }
    
}
