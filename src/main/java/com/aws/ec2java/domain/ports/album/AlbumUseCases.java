package com.aws.ec2java.domain.ports.album;

import java.util.List;
import java.util.Optional;

import com.aws.ec2java.application.command.CreateAlbumCommand;
import com.aws.ec2java.domain.models.Album;

public interface AlbumUseCases {
    Album addAlbum(CreateAlbumCommand newAlbum);
    void deleteAlbum(Album album);
    Optional<Album>getAlbum(String name);
    List<Album> getAllAlbums();
}
