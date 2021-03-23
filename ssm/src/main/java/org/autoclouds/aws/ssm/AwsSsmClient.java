package org.autoclouds.aws.ssm;

import software.amazon.awssdk.services.ssm.SsmClient;

public class AwsSsmClient {
    public AwsSsmClient(SsmClient client) {
        this.client = client;
    }

    private SsmClient client;



}
