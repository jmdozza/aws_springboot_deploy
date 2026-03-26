package com.aws.ec2java.infraestructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.ec2java.application.ArtistService;
import com.aws.ec2java.domain.models.Artist;
import com.aws.ec2java.infraestructure.DTO.request.ArtistRequest;
import com.aws.ec2java.infraestructure.DTO.response.ArtistResponse;
import com.aws.ec2java.infraestructure.mappers.ArtistMapper;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/aws")
@AllArgsConstructor
public class SampleController {

    private final ArtistService artistService;
    private final ArtistMapper artistMapper;
    
    @GetMapping
    public ResponseEntity<String> getMethodName() {
        return ResponseEntity.ok("Hello Aws World, this is a get response");
    }   

    @PostMapping
    public ResponseEntity<ArtistResponse> postMethodName(@RequestBody ArtistRequest newArt) {
        Artist createdArtist= artistService.createArtist(artistMapper.toDomain(newArt));
        return ResponseEntity.status(HttpStatus.CREATED).body(artistMapper.toResponse(createdArtist));
    }
    
}
