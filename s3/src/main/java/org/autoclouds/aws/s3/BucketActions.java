package org.autoclouds.aws.s3;

import org.autoclouds.aws.s3.parameters.CopyParameters;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BucketActions {
    private final S3Client client;
    private final Requests requests;

    public BucketActions(S3Client client, String bucketName) {
        this.client = client;
        this.requests = Requests.forBucket(bucketName);
    }

    public List<String> uploadMultipleFiles(Map<File, String> filesToUpload) {
        return filesToUpload
                .entrySet()
                .stream()
                .map(entry -> uploadFile(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public String uploadFile(File sourceFile, String destinationKey) {
        PutObjectRequest request = requests.createPutObjectRequest(destinationKey);
        return client.putObject(request, sourceFile.toPath()).eTag();
    }

    public List<String> copyKeys(List<CopyParameters> copyParametersList) {
        return copyParametersList.stream()
                .map(this::copyKey)
                .collect(Collectors.toList());
    }

    public String copyKey(CopyParameters copyParameters) {
        CopyObjectRequest copyObjectRequest = requests.createCopyObjectRequest(copyParameters);
        return client.copyObject(copyObjectRequest).copyObjectResult().eTag();
    }

    public List<DeletedObject> deleteKeys(List<String> keys) {
        DeleteObjectsRequest request = requests.createDeleteObjectsRequest(keys);
        return client.deleteObjects(request).deleted();

        /*return deleteKeyRequestList.stream()
                .map(this::deleteKey)
                .collect(Collectors.toList());*/
    }

    public String deleteKey(String key) {
        DeleteObjectRequest request = requests.createDeleteObjectRequest(key);
        return client.deleteObject(request).toString();
    }

    public void downloadKeyToFile(String key, File destinationFile) {
        GetObjectRequest request = requests.createGetObjectRequest(key);
        client.getObject(request, destinationFile.toPath());
    }
}
