package com.aws.ec2java.infraestructure.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.ec2java.application.GenreService;
import com.aws.ec2java.domain.models.Genre;
import com.aws.ec2java.infraestructure.DTO.request.GenreRequest;
import com.aws.ec2java.infraestructure.DTO.response.GenreResponse;
import com.aws.ec2java.infraestructure.mappers.GenreMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/genre")
@Validated
@AllArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @PostMapping
    ResponseEntity<GenreResponse> createGenre(@Valid @RequestBody GenreRequest genreName){
        Genre newGenr = genreService.addGenre(genreName.name());
        return  ResponseEntity.ok(genreMapper.toResponse(newGenr));
    }

    @GetMapping
    ResponseEntity<List<GenreResponse>> getAllGenres() {
        List<GenreResponse> genres = genreService.getAllGenres().stream()
                .map(genreMapper::toResponse)
                .toList();
        return ResponseEntity.ok(genres);
    }
    
}
