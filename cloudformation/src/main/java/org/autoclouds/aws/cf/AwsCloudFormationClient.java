package org.autoclouds.aws.cf;

import software.amazon.awssdk.services.cloudformation.CloudFormationClient;

public class AwsCloudFormationClient {
    private CloudFormationClient client;

    public AwsCloudFormationClient(CloudFormationClient client) {
        this.client = client;
    }
}
