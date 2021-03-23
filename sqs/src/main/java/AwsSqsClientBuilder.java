import org.autoclouds.aws.Authentication;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.SqsClientBuilder;

public class AwsSqsClientBuilder implements Authentication<AwsSqsClient> {
    private SqsClientBuilder sqsClientBuilder;

    private AwsSqsClientBuilder(Region region) {
        sqsClientBuilder = SqsClient.builder().region(region);
    }

    public static AwsSqsClientBuilder forRegion(Region region) {
        return new AwsSqsClientBuilder(region);
    }


    @Override
    public AwsSqsClient createWithDefaultProfile() {
        return new AwsSqsClient(sqsClientBuilder.build());
    }

    @Override
    public AwsSqsClient createWithProfile(String profileName) {
        return new AwsSqsClient(sqsClientBuilder.credentialsProvider(ProfileCredentialsProvider.create(profileName)).build());
    }

    @Override
    public AwsSqsClient createWithCredentials(String accessKeyId, String secretAccessKey) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        return new AwsSqsClient(sqsClientBuilder.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build());
    }
}
