package com.aws.ec2java.infraestructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.ec2java.infraestructure.entitys.GenreEntity;

@Repository
public interface JpaGenreRepository extends JpaRepository<GenreEntity, UUID> {
}
