package org.autoclouds.aws.s3;

import org.autoclouds.aws.Authentication;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

import java.net.URI;

public class AwsS3ClientBuilder implements Authentication<AwsS3Client> {
    private S3ClientBuilder s3ClientBuilder;

    private AwsS3ClientBuilder(Region region) {
        s3ClientBuilder = S3Client.builder().region(region);
    }

    public static AwsS3ClientBuilder forRegion(Region region) {
        return new AwsS3ClientBuilder(region);
    }

    public AwsS3ClientBuilder withEndpoint(URI endpointOverride) {
        s3ClientBuilder.endpointOverride(endpointOverride);
        return this;
    }

    @Override
    public AwsS3Client createWithDefaultProfile() {
        return new AwsS3Client(s3ClientBuilder.build());
    }

    @Override
    public AwsS3Client createWithProfile(String profileName) {
        return new AwsS3Client(s3ClientBuilder.credentialsProvider(ProfileCredentialsProvider.create(profileName)).build());
    }

    @Override
    public AwsS3Client createWithCredentials(String accessKeyId, String secretAccessKey) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        return new AwsS3Client(s3ClientBuilder.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build());
    }
}
