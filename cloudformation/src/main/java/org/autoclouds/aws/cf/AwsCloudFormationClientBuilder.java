package org.autoclouds.aws.cf;

import org.autoclouds.aws.Authentication;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.CloudFormationClientBuilder;

public class AwsCloudFormationClientBuilder implements Authentication<AwsCloudFormationClient> {
    private CloudFormationClientBuilder clientBuilder;

    private AwsCloudFormationClientBuilder(Region region) {
        this.clientBuilder = CloudFormationClient.builder().region(region);
    }

    public static AwsCloudFormationClientBuilder forRegion(Region region) {
        return new AwsCloudFormationClientBuilder(region);
    }

    @Override
    public AwsCloudFormationClient createWithDefaultProfile() {
        return new AwsCloudFormationClient(clientBuilder.build());
    }

    @Override
    public AwsCloudFormationClient createWithProfile(String profileName) {
        return new AwsCloudFormationClient(clientBuilder.credentialsProvider(ProfileCredentialsProvider.create(profileName)).build());
    }

    @Override
    public AwsCloudFormationClient createWithCredentials(String accessKeyId, String secretAccessKey) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        return new AwsCloudFormationClient(clientBuilder.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build());
    }
}
