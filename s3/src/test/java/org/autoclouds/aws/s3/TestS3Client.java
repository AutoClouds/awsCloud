package org.autoclouds.aws.s3;

import software.amazon.awssdk.regions.Region;

public class TestS3Client {
    public void test() {
        //AwsS3Client client = AwsS3ClientBuilder.forRegion(Region.AP_SOUTH_1).createWithDefaultProfile();
        AwsS3Client awsS3Client = AwsS3ClientBuilder.forRegion(Region.AP_SOUTHEAST_1).createWithProfile("");
    }
}
