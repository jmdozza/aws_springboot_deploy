package com.aws.ec2java.application;
import com.aws.ec2java.domain.models.Song;
import com.aws.ec2java.domain.models.SongFile;
import com.aws.ec2java.domain.ports.out.FileStoragePort;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3Service implements FileStoragePort {
    
    //Inyectamos el cliente s3 y el bucket name
    private final S3Client s3Client;
    private final String bucketName;

    public S3Service(S3Client s3Client, String bucketName){
        this.s3Client=s3Client;
        this.bucketName = bucketName;
    }

    @Override
    public void uploadFile(SongFile songFile) {
        try{
        s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(songFile.audioFileName()) //File name
                            .build(),
                            RequestBody.fromBytes(songFile.audioFileBytes())
                            );
        }catch (S3Exception e){
            throw new RuntimeException("Aws rechazó la petición");
        }catch (SdkClientException e){
            throw new RuntimeException("Error del cliente");
        }
    }

    @Override
    public SongFile downloadFile(String objectKey) {
        GetObjectRequest getObjectRequest = GetObjectRequest.
                                            builder()
                                            .bucket(bucketName)
                                            .key(objectKey)
                                            .build();
        
        ResponseBytes<GetObjectResponse> objBytes=s3Client.getObjectAsBytes(getObjectRequest);
        SongFile songFile= SongFile.builder()
                                    .audioFileBytes(objBytes.asByteArray())
                                    .audioFileName(objectKey)
                                    .build();
        return songFile;
    }

    @Override
    public void deleteFile(String objectKey) {
        s3Client.deleteObject(DeleteObjectRequest.builder().
                            bucket(bucketName)
                            .key(objectKey)
                            .build()
                            );                        
    }
}
