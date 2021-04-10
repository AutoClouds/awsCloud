package org.autoclouds.aws.s3.old.actions;

import software.amazon.awssdk.services.s3.S3Client;

import java.util.Map;

public class S3Configuration extends org.autoclouds.aws.s3.old.actions.S3Client {
    private S3Configuration() {
        super();
    }

   /* public static Map<String, NotificationConfiguration> getNotificationConfig(BaseS3 bucket) {
        return amazonS3.getBucketNotificationConfiguration(bucket.getBucketName())
                .getConfigurations();
    }*/
}
