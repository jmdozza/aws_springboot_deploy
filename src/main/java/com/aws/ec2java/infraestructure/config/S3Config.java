package com.aws.ec2java.infraestructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aws.ec2java.application.S3Service;
import com.aws.ec2java.domain.ports.out.FileStoragePort;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    //@Value("${cloud.aws.credentials.accessKey}")
    //private String accesKey;
    //@Value("${cloud.aws.credentials.secretKey}")
    //private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public S3Client s3Client(){
        //AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accesKey, 
                                                                            //secretKey);
        return S3Client.builder()
                        .region(Region.of(region))
                        //.credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                        .build();
    }

    @Bean
    public FileStoragePort fileStoragePort(
            S3Client s3Client,
            @Value("${cloud.aws.s3.bucket.name}") String bucketName
    ) {
        return new S3Service(s3Client, bucketName);
    }
    
}
