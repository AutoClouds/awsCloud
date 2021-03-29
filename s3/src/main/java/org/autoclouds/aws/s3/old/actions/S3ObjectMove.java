package aws.s3.actions;

import aws.s3.requests.MoveObjectRequest;
import lombok.val;


/*

Static should be deleted
Initialization using constructor
moveS3Object have to return true-false/amount of moved objects
 */


public class S3ObjectMove extends S3Client {

    private S3ObjectMove() {
    }

    public static void moveS3Object(final MoveObjectRequest moveObjectRequest) {
        val sourceBucket = moveObjectRequest.getSourceBucket();
        val sourceKey = moveObjectRequest.getSourceKey();
        val destinationBucket = moveObjectRequest.getDestinationBucket();
        val destinationKey = moveObjectRequest.getDestinationKey();

        amazonS3.copyObject(sourceBucket, sourceKey, destinationBucket, destinationKey);
        amazonS3.deleteObject(sourceBucket, sourceKey);
    }
}
