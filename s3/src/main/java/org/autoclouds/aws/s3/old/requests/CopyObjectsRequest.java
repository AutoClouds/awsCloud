package aws.s3.requests;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CopyObjectsRequest {
    private List<S3ObjectSummary> s3ObjectSummaryList;
    @Builder.Default private String destinationBucket = "";
    @Builder.Default private String destinationPath = "";
}
