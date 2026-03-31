package com.aws.ec2java.infraestructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.ec2java.infraestructure.entitys.ArtistEntity;
@Repository
public interface JpaArtistRepository extends JpaRepository<ArtistEntity,UUID> {
    Optional<ArtistEntity> findByArtisticnameContaining(String name);
    
}
