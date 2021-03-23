package org.autoclouds.aws.ssm;

import org.autoclouds.aws.Authentication;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.SsmClientBuilder;

public class AwsSsmClientBuilder implements Authentication<AwsSsmClient> {

    private SsmClientBuilder ssmClientBuilder;

    private AwsSsmClientBuilder(Region region) {
        ssmClientBuilder = SsmClient.builder().region(region);
    }

    public static AwsSsmClientBuilder forRegion(Region region) {
        return new AwsSsmClientBuilder(region);
    }


    @Override
    public AwsSsmClient createWithDefaultProfile() {
        return new AwsSsmClient(ssmClientBuilder.build());
    }

    @Override
    public AwsSsmClient createWithProfile(String profileName) {
        return new AwsSsmClient(ssmClientBuilder.credentialsProvider(ProfileCredentialsProvider.create(profileName)).build());
    }

    @Override
    public AwsSsmClient createWithCredentials(String accessKeyId, String secretAccessKey) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        return new AwsSsmClient(ssmClientBuilder.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build());
    }
}
