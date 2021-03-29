package aws.s3.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DoesObjectExistRequest {
    private String bucketName;
    private String key;
}
