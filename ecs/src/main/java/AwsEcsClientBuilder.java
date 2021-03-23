import org.autoclouds.aws.Authentication;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.ecs.EcsClientBuilder;

public class AwsEcsClientBuilder implements Authentication<AwsEcsClient> {

    private EcsClientBuilder ecsClientBuilder;

    private AwsEcsClientBuilder(Region region) {
        ecsClientBuilder = EcsClient.builder().region(region);
    }

    public static AwsEcsClientBuilder forRegion(Region region) {
        return new AwsEcsClientBuilder(region);
    }


    @Override
    public AwsEcsClient createWithDefaultProfile() {
        return new AwsEcsClient(ecsClientBuilder.build());
    }

    @Override
    public AwsEcsClient createWithProfile(String profileName) {
        return new AwsEcsClient(ecsClientBuilder.credentialsProvider(ProfileCredentialsProvider.create(profileName)).build());
    }

    @Override
    public AwsEcsClient createWithCredentials(String accessKeyId, String secretAccessKey) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        return new AwsEcsClient(ecsClientBuilder.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build());
    }
}
