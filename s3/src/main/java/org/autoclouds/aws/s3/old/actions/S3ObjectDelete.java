package aws.s3.actions;

import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;


/*

Static should be deleted
Initialization using constructor
deleteObject have to return answer
deleteObjects have to return true-false/amount of deleted objects
 */

@Slf4j
public class S3ObjectDelete extends S3Client {

    private S3ObjectDelete() {
    }

    public static void deleteObject(String bucketName, String key) {
        log.info("Deleting one object from bucket {} with key {}", bucketName, key);
        amazonS3.deleteObject(bucketName, key);
    }

    public static void deleteObjects(String bucketName, String prefix) {
        log.info("Deleting objects from bucket {} with prefix {}", bucketName, prefix);
        List<String> keys = S3ObjectRetriever.getKeys(bucketName, prefix);
        if (!keys.isEmpty()) {
            val result = deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys.toArray(new String[0])));
            log.info("Deleted {} objects", result.getDeletedObjects().size());
        } else {
            log.info("Nothing to delete");
        }
    }

    private static DeleteObjectsResult deleteObjects(final DeleteObjectsRequest deleteObjectsRequest) {
        return amazonS3.deleteObjects(deleteObjectsRequest);
    }

}