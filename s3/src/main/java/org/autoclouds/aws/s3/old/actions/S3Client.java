package org.autoclouds.aws.s3.old.actions;

/*import aws.AwsClientFactory;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;*/

abstract class S3Client {

    S3Client() {
    }

    /*static AmazonS3 amazonS3 = AwsClientFactory.getS3Client();

    static TransferManager getTransferManager() {
        return TransferManagerBuilder
                .standard()
                .withS3Client(amazonS3)
                .build();
    }*/
}
