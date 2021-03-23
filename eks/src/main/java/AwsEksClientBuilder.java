import org.autoclouds.aws.Authentication;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eks.EksClient;
import software.amazon.awssdk.services.eks.EksClientBuilder;

public class AwsEksClientBuilder implements Authentication<AwsEksClient> {

    private EksClientBuilder eksClientBuilder;

    private AwsEksClientBuilder(Region region) {
        eksClientBuilder = EksClient.builder().region(region);
    }

    public static AwsEksClientBuilder forRegion(Region region) {
        return new AwsEksClientBuilder(region);
    }


    @Override
    public AwsEksClient createWithDefaultProfile() {
        return new AwsEksClient(eksClientBuilder.build());
    }

    @Override
    public AwsEksClient createWithProfile(String profileName) {
        return new AwsEksClient(eksClientBuilder.credentialsProvider(ProfileCredentialsProvider.create(profileName)).build());
    }

    @Override
    public AwsEksClient createWithCredentials(String accessKeyId, String secretAccessKey) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        return new AwsEksClient(eksClientBuilder.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build());
    }
}
