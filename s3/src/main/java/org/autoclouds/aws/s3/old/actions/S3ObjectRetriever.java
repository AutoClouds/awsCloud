package aws.s3.actions;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
getSummaries, getKeys have to be renamed
Static should be deleted
Initialization using constructor using full path
downloadBucketToFolder, downloadToFile, asyncDownloadToFile,
 */


@Slf4j
public class S3ObjectRetriever extends S3Client {

    private S3ObjectRetriever() {
    }

    public static List<S3ObjectSummary> getSummaries(String bucketName, String prefix) {
        log.info("Getting summaries for '{}' from bucket '{}'", prefix, bucketName);
        ObjectListing listing = amazonS3.listObjects(bucketName, prefix);
        List<S3ObjectSummary> summaries = listing.getObjectSummaries();
        while (listing.isTruncated()) {
            listing = amazonS3.listNextBatchOfObjects(listing);
            summaries.addAll(listing.getObjectSummaries());
        }
        return summaries;
    }

    public static List<String> getKeys(String bucketName, String prefix) {
        return getSummaries(bucketName, prefix).stream().map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public static String downloadAsString(String bucketName, String key) {
        log.info("Downloading file '{}' from bucket '{}'", key, bucketName);
        try (InputStream inputStream = amazonS3.getObject(bucketName, key).getObjectContent()) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
    }

    @SneakyThrows
    public static List<String> downloadAsList(String bucketName, String key) {
        log.info("Downloading file '{}' from bucket '{}'", key, bucketName);
        try (InputStream inputStream = amazonS3.getObject(bucketName, key).getObjectContent();
             Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
            return lines.collect(Collectors.toList());
        }
    }

    @SneakyThrows
    public static void asyncDownloadToFile(String bucketName, String key, File file) {
        log.info("Downloading file '{}' from bucket '{}' to '{}'", key, bucketName, file.getPath());
        getTransferManager().download(bucketName, key, file);
    }

    @SneakyThrows
    public static void downloadToFile(String bucketName, String key, File file) {
        log.info("Downloading file '{}' from bucket '{}' to '{}'", key, bucketName, file.getPath());
        getTransferManager().download(bucketName, key, file).waitForCompletion();
    }

    @SneakyThrows
    public static void downloadBucketToFolder(String bucketName, String key, File file) {
        log.info("Downloading folder '{}' from bucket '{}' to '{}'", key, bucketName, file.getPath());
        getTransferManager().downloadDirectory(bucketName, key, file).waitForCompletion();
    }
}
