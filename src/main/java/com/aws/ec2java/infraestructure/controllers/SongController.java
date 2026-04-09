package com.aws.ec2java.infraestructure.controllers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aws.ec2java.application.SongService;
import com.aws.ec2java.application.command.UploadSongCommand;
import com.aws.ec2java.domain.models.Song;
import com.aws.ec2java.infraestructure.DTO.request.SongRequest;
import com.aws.ec2java.infraestructure.DTO.response.SongFileResponse;
import com.aws.ec2java.infraestructure.DTO.response.SongResponse;
import com.aws.ec2java.infraestructure.mappers.SongFileMapper;
import com.aws.ec2java.infraestructure.mappers.SongMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;



@RestController
@RequestMapping("/api/v1/song")
@AllArgsConstructor
public class SongController {

    private final SongService songService;
    private final SongMapper songMapper;
    private final SongFileMapper songFileMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SongResponse> addSong(
        @RequestPart("file") MultipartFile audioFile,
        @RequestPart("metadata") @Valid SongRequest medatadata
    ) throws IOException{
        UploadSongCommand uploadSongCommand= UploadSongCommand.builder()
                                            .title(medatadata.title())
                                            .album_id(medatadata.album_id())
                                            .genre_ids(medatadata.genre_ids())
                                            .audioFileBytes(audioFile.getBytes())
                                            .originalFileName(audioFile.getOriginalFilename())
                                            .contentType(audioFile.getContentType())
                                            .build();

        Song newSong = songService.addSong(uploadSongCommand);
        return ResponseEntity.ok(songMapper.toResponse(newSong));
    }

    @GetMapping
    public ResponseEntity<List<SongResponse>> getAllSongs() {
        List<Song> songsResp= songService.getSongs();
        return ResponseEntity.ok(songsResp.stream()
                                            .map(songMapper::toResponse)
                                            .toList());
    }

    @GetMapping("/{songId}")
    public ResponseEntity<SongResponse> getSongById(@PathVariable("songId") UUID songP) {
        SongResponse songResponse= songService.getSongById(songP).map(songMapper::toResponse).orElseThrow(()->new EntityNotFoundException("Song not Found"));
        return ResponseEntity.ok(songResponse);
    }
    

    @GetMapping("/{songId}/download")
    public ResponseEntity<byte[]> getMethodName(@PathVariable("songId") UUID songP) {
        SongResponse songResponse= songService.getSongById(songP).map(songMapper::toResponse).orElseThrow(()->new EntityNotFoundException("Song not Found"));
        SongFileResponse songFileResponse= songFileMapper.toResponse(songService.getSongFile(songResponse.audioKey()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.setContentDispositionFormData("attachment", songResponse.title()+".mp3");
        return ResponseEntity.ok()
                            .headers(headers)
                            .body(songFileResponse.songBytes());
    }
    
    

    
}
