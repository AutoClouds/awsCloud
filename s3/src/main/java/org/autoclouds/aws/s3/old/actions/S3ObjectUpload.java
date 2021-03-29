package aws.s3.actions;

import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;


/*

Static should be deleted
Initialization using constructor using full path
async methods have to be deleted
methods have to return true-false
 */

@Slf4j
public class S3ObjectUpload extends S3Client {

    private S3ObjectUpload() {
    }

    private static final String UPLOAD_LOG = "Uploading file '{}' to bucket '{}'";

    public static void asyncUploadFile(String bucketName, String key, File file) {
        log.info("Async uploading file '{}' to bucket '{}'", key, bucketName);
        getTransferManager().upload(bucketName, key, file);
    }

    public static void asyncUploadDirectory(String bucketName, String key, File file) {
        log.info(UPLOAD_LOG, key, bucketName);
        getTransferManager().uploadDirectory(bucketName, key, file, true);
    }

    @SneakyThrows
    public static void uploadFile(PutObjectRequest putObjectRequest) {
        log.info(UPLOAD_LOG, putObjectRequest.getKey(), putObjectRequest.getBucketName());
        getTransferManager().upload(putObjectRequest).waitForCompletion();
    }

    @SneakyThrows
    public static void uploadFile(String bucketName, String key, File file) {
        log.info(UPLOAD_LOG, key, bucketName);
        getTransferManager().upload(bucketName, key, file).waitForCompletion();
    }

    @SneakyThrows
    public static void uploadDirectory(String bucketName, String key, File file) {
        log.info(UPLOAD_LOG, key, bucketName);
        getTransferManager().uploadDirectory(bucketName, key, file, true).waitForCompletion();
    }

}
