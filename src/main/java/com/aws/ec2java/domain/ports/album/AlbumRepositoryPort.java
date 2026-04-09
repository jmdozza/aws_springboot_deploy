package com.aws.ec2java.domain.ports.album;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.aws.ec2java.domain.models.Album;

public interface AlbumRepositoryPort {
    Album insertAlbum(Album newAlbum);
    Optional<Album> getAlbumById(UUID albumId);
    List<Album> getAllAlbums();
}
