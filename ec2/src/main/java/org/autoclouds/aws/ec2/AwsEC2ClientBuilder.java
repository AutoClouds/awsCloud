package org.autoclouds.aws.ec2;

import org.autoclouds.aws.Authentication;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.Ec2ClientBuilder;

public class AwsEC2ClientBuilder  implements Authentication<AwsEC2Client> {
    private Ec2ClientBuilder clientBuilder;

    private AwsEC2ClientBuilder(Region region) {
        this.clientBuilder = Ec2Client.builder().region(region);
    }

    public static AwsEC2ClientBuilder forRegion(Region region) {
        return new AwsEC2ClientBuilder(region);
    }

    @Override
    public AwsEC2Client createWithDefaultProfile() {
        return new AwsEC2Client(clientBuilder.build());
    }

    @Override
    public AwsEC2Client createWithProfile(String profileName) {
        return new AwsEC2Client(clientBuilder.credentialsProvider(ProfileCredentialsProvider.create(profileName)).build());
    }

    @Override
    public AwsEC2Client createWithCredentials(String accessKeyId, String secretAccessKey) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        return new AwsEC2Client(clientBuilder.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build());
    }
}
