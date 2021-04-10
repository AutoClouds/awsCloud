package org.autoclouds.aws.s3;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest;

import java.util.List;
import java.util.stream.Collectors;

public class AwsS3Client {
    private final S3Client client;

    AwsS3Client(S3Client client) {
        this.client = client;
    }

    public BucketActions onBucket(String bucketName) {
        return new BucketActions(client, bucketName);
    }

    public String createBucketWith(String bucketName) {
        CreateBucketRequest request = CreateBucketRequest.builder().bucket(bucketName).build();
        return client.createBucket(request).location();
    }

    public String deleteBucketWith(String bucketName) {
        DeleteBucketRequest request = DeleteBucketRequest.builder().bucket(bucketName).build();
        return client.deleteBucket(request).toString();
    }

    public List<String> getBucketNameList() {
        return client.listBuckets().buckets().stream().map(Bucket::name).collect(Collectors.toList());
    }
}
