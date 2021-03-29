package aws.s3.requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MoveObjectRequest {
    private String sourceBucket;
    private String destinationBucket;
    private String sourceKey;
    private String destinationKey;
}
