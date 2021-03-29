package aws.s3.actions;

import aws.s3.buckets.BaseS3;
import com.amazonaws.services.s3.model.NotificationConfiguration;

import java.util.Map;

public class S3Configuration extends S3Client {
    private S3Configuration() {
        super();
    }

    public static Map<String, NotificationConfiguration> getNotificationConfig(BaseS3 bucket) {
        return amazonS3.getBucketNotificationConfiguration(bucket.getBucketName())
                .getConfigurations();
    }
}
