package org.autoclouds.aws.s3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.regions.Region;

import java.net.URI;

@Testcontainers
public class BucketActionTests {
    private static AwsS3Client client = null;

    @Container
    private static final LocalStackContainer LOCAL_STACK_CONTAINER = new LocalStackContainer(DockerImageName.parse("localstack/localstack:latest"))
            .withServices(LocalStackContainer.Service.S3);

    @BeforeAll
    static void initClient() {
        Region region = Region.of(LOCAL_STACK_CONTAINER.getRegion());
        String accessKey = LOCAL_STACK_CONTAINER.getAccessKey();
        String secretKey = LOCAL_STACK_CONTAINER.getSecretKey();
        URI endpoint = LOCAL_STACK_CONTAINER.getEndpointOverride(LocalStackContainer.Service.S3);
        client = AwsS3ClientBuilder
                .forRegion(region)
                .withEndpoint(endpoint)
                .createWithCredentials(accessKey, secretKey);
    }

    @ParameterizedTest(name = "Check bucket with name {0} can be created")
    @ValueSource(strings = {"testbucket"})
    void checkBucketWithSpecifiedNameCanBeCreated(String bucketName) {
        String bucketLocation = client.createBucketWith(bucketName);
        Assertions.assertEquals("/".concat(bucketName), bucketLocation);
    }
}
