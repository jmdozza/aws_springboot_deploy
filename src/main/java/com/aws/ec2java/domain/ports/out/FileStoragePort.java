package com.aws.ec2java.domain.ports.out;
import com.aws.ec2java.domain.models.SongFile;

public interface FileStoragePort {
    void uploadFile(SongFile song);
    SongFile downloadFile(String objectKey);
    void deleteFile(String objectKey);
}
