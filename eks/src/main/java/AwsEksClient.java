import software.amazon.awssdk.services.eks.EksClient;

public class AwsEksClient {
    public AwsEksClient(EksClient eksClient) {
        this.eksClient = eksClient;
    }

    private EksClient eksClient;

}
