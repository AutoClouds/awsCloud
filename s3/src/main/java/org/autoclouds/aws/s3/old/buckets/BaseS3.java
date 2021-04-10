package org.autoclouds.aws.s3.old.buckets;


public abstract class BaseS3 {
    private String bucketName;

    public BaseS3(String bucketName) {
        this.bucketName = bucketName;
    }
}
