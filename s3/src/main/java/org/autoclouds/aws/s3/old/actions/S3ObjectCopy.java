package org.autoclouds.aws.s3.old.actions;

import org.autoclouds.aws.s3.old.requests.CopyObjectsRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
/*import com.amazonaws.services.s3.model.CopyObjectRequest;
import lombok.val;*/



/*

Static should be deleted
Initialization using constructor
copyS3Object have to return answer
 */

public class S3ObjectCopy extends org.autoclouds.aws.s3.old.actions.S3Client {

    private S3ObjectCopy() {
    }

    public static void copyS3Object(final CopyObjectRequest copyObjectRequest) {
        //getTransferManager().copy(copyObjectRequest);
    }

    public static void copyS3Objects(final CopyObjectsRequest copyObjectsRequest) {
        /*val destinationPath = copyObjectsRequest.getDestinationPath();
        copyObjectsRequest.getS3ObjectSummaryList().parallelStream().forEach(s3ObjectSummary -> {
            val sourceBucket = s3ObjectSummary.getBucketName();
            val destinationBucket = copyObjectsRequest.getDestinationBucket();
            val sourceKey = s3ObjectSummary.getKey();
            val tempKey = getFileName(sourceKey);
            val destinationKey = destinationPath.isEmpty() ? tempKey : String.format("%s/%s", destinationPath, tempKey);
            copyS3Object(new CopyObjectRequest(sourceBucket, sourceKey, destinationBucket, destinationKey));
        });*/
    }

    /*private static String getFileName(String sourceKey) {
        val pathArray = sourceKey.split("/");
        return pathArray[pathArray.length - 1];
    }*/
}
