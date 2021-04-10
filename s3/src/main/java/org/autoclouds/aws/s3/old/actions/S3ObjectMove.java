package org.autoclouds.aws.s3.old.actions;

import org.autoclouds.aws.s3.old.requests.MoveObjectRequest;
import software.amazon.awssdk.services.s3.S3Client;


/*

Static should be deleted
Initialization using constructor
moveS3Object have to return true-false/amount of moved objects
 */


public class S3ObjectMove extends org.autoclouds.aws.s3.old.actions.S3Client {

    private S3ObjectMove() {
    }

    public static void moveS3Object(final MoveObjectRequest moveObjectRequest) {
        /*val sourceBucket = moveObjectRequest.getSourceBucket();
        val sourceKey = moveObjectRequest.getSourceKey();
        val destinationBucket = moveObjectRequest.getDestinationBucket();
        val destinationKey = moveObjectRequest.getDestinationKey();

        amazonS3.copyObject(sourceBucket, sourceKey, destinationBucket, destinationKey);
        amazonS3.deleteObject(sourceBucket, sourceKey);*/
    }
}
