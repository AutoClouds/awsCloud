package org.autoclouds.aws.s3;

import org.autoclouds.aws.s3.parameters.CopyParameters;
import software.amazon.awssdk.services.s3.model.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Requests {
    private final String bucketName;

    private Requests(String bucketName) {
        this.bucketName = bucketName;
    }

    public static Requests forBucket(String bucketName) {
        return new Requests(bucketName);
    }

    public PutObjectRequest createPutObjectRequest(String destinationKey) {
        return PutObjectRequest.builder()
                .bucket(bucketName)
                .key(destinationKey)
                .build();
    }

    public CopyObjectRequest createCopyObjectRequest(CopyParameters copyParameters) {
        return  CopyObjectRequest.builder()
                .copySource(getSourcePathInUrlEnding(copyParameters.getSourceKey()))
                .destinationBucket(copyParameters.getDestinationBucket())
                .destinationKey(copyParameters.getDestinationKey())
                .build();
    }

    private String getSourcePathInUrlEnding(String sourceKey) {
        try {
            return URLEncoder.encode(bucketName + "/" + sourceKey, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public DeleteObjectRequest createDeleteObjectRequest(String key) {
        return DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
    }

    public DeleteObjectsRequest createDeleteObjectsRequest(List<String> keys) {
        Delete delete = Delete
                .builder()
                .objects(createObjectIdList(keys))
                .build();

        return DeleteObjectsRequest
                .builder()
                .bucket(bucketName)
                .delete(delete)
                .build();
    }

    private ArrayList<ObjectIdentifier> createObjectIdList(List<String> keys) {
        return keys
                .stream()
                .map(key -> ObjectIdentifier.builder().key(key).build())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public GetObjectRequest createGetObjectRequest(String key) {
        return GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
    }
}
