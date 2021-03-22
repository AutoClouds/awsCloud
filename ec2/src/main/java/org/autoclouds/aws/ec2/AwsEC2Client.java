package org.autoclouds.aws.ec2;

import software.amazon.awssdk.services.ec2.Ec2Client;

public class AwsEC2Client {
    private Ec2Client client;

    public AwsEC2Client(Ec2Client client) {
        this.client = client;
    }
}
