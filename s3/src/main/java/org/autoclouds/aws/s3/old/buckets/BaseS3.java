package aws.s3.buckets;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseS3 {
    @Getter
    private String bucketName;

    public BaseS3(String bucketName) {
        this.bucketName = bucketName;
    }
}
