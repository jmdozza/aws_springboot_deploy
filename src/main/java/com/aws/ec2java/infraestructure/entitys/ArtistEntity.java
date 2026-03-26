package com.aws.ec2java.infraestructure.entitys;

import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
@AllArgsConstructor
@Table(name="artist")
public class ArtistEntity {
    @Id
    @GeneratedValue(
        strategy = GenerationType.UUID
    )
    @Column(name="uuid")
    private UUID uuid;

    @Column(name="name")
    private String name;

    @Column(name="artisticname")
    private String artisticname;

    @Column(name = "age")
    private int age;
    
}
