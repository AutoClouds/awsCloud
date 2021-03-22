package org.autoclouds.aws.s3;

import software.amazon.awssdk.services.s3.S3Client;

public class AwsS3Client {
    private S3Client client;

    AwsS3Client(S3Client client) {
        this.client = client;
    }
}
