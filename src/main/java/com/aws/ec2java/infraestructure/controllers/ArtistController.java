package com.aws.ec2java.infraestructure.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.ec2java.application.ArtistService;
import com.aws.ec2java.domain.models.Artist;
import com.aws.ec2java.infraestructure.DTO.request.ArtistRequest;
import com.aws.ec2java.infraestructure.DTO.response.ArtistResponse;
import com.aws.ec2java.infraestructure.mappers.ArtistMapper;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/artist")
@AllArgsConstructor
@Validated
public class ArtistController {

    private final ArtistService artistService;
    private final ArtistMapper artistMapper;
    
    @GetMapping
    public ResponseEntity<List<ArtistResponse>> getClients() {
        List<ArtistResponse> artists= artistService.getAllArtists().stream().map(artistMapper::toResponse).toList();
        return ResponseEntity.ok(artists);
    }   

    @PostMapping
    public ResponseEntity<ArtistResponse> createArtist(@Valid @RequestBody ArtistRequest newArt) {
        Artist createdArtist= artistService.createArtist(artistMapper.toDomain(newArt));
        return ResponseEntity.status(HttpStatus.CREATED).body(artistMapper.toResponse(createdArtist));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ArtistResponse> getArtist(@PathVariable @NotBlank String name) {
        Artist artist = artistService.getArtist(name).get();
        return ResponseEntity.ok(artistMapper.toResponse(artist));
    }
    
}
