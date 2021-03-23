import software.amazon.awssdk.services.sqs.SqsClient;

public class AwsSqsClient {

    public AwsSqsClient(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    private SqsClient sqsClient;

}
