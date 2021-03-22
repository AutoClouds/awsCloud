package org.autoclouds.aws.cf;

import software.amazon.awssdk.regions.Region;

public class ClientTest {
    public void test() {
        AwsCloudFormationClient client = AwsCloudFormationClientBuilder.forRegion(Region.AP_SOUTH_1).createWithDefaultProfile();
    }
}
