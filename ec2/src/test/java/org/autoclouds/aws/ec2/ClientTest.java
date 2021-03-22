package org.autoclouds.aws.ec2;

import software.amazon.awssdk.regions.Region;

public class ClientTest {
    public void test() {
        AwsEC2Client client = AwsEC2ClientBuilder.forRegion(Region.AP_SOUTH_1).createWithDefaultProfile();
    }
}
