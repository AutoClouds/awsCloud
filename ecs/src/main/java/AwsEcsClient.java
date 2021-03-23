import software.amazon.awssdk.services.ecs.EcsClient;

public class AwsEcsClient {
    public AwsEcsClient(EcsClient escClient) {
        this.escClient = escClient;
    }

    private EcsClient escClient;


}
