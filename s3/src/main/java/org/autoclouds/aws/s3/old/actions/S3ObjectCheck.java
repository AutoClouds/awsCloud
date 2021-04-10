package org.autoclouds.aws.s3.old.actions;

/*import lombok.extern.slf4j.Slf4j;
import org.awaitility.core.ConditionTimeoutException;*/
import software.amazon.awssdk.services.s3.S3Client;

import static java.util.concurrent.TimeUnit.SECONDS;
/*import static org.awaitility.Awaitility.await;*/


/*

Static should be deleted
Public methods: waitAndCheckIfObjectExists, doesObjectExist, waitAndCheckIfObjectExists
Initialization using constructor

 */

public class S3ObjectCheck extends org.autoclouds.aws.s3.old.actions.S3Client {

    private S3ObjectCheck() {
    }

    private static final int DEFAULT_TIMEOUT = 30;

    /*public static boolean doesObjectExist(String bucketName, String key) {
        return amazonS3.doesObjectExist(bucketName, key);
    }*/

    /**
     * @param key     - exact name of object key to wait for
     * @return true - if object was found within DEFAULT_TIMEOUT. false - if not found.
     */
    /*public static boolean waitAndCheckIfObjectExists(String bucketName, String key) {
        return waitAndCheckIfObjectExists(bucketName, key, DEFAULT_TIMEOUT);
    }*/

    /**
     * @param key     - exact name of object key to wait for
     * @param timeout - timeout in seconds
     * @return true - if object was found within given timeout. false - if not found.
     */
    /*public static boolean waitAndCheckIfObjectExists(String bucketName, String key, int timeout) {
        try {
            waitForObject(bucketName, key, timeout);
        } catch (ConditionTimeoutException e) {
            return false;
        }
        return true;
    }*/

    /**
     * Use when:
     * - waiting for 1 object
     * - object key is known
     *
     * @param key - exact name of object key to wait for
     * @throws ConditionTimeoutException If condition was not fulfilled within DEFAULT_TIMEOUT.
     */
    public static void waitForObject(String bucketName, String key) {
        //waitForObject(bucketName, key, DEFAULT_TIMEOUT);
    }

    /**
     * Use when:
     * - waiting for 1 object
     * - object key is known
     *
     * @param key     - exact name of object key to wait for
     * @param timeout - timeout in seconds
     * @throws ConditionTimeoutException If condition was not fulfilled within the given time period.
     */
    /*public static void waitForObject(String bucketName, String key, int timeout) {
        log.info("Waiting {}s for '{}' in bucket '{}'...", timeout, key, bucketName);
        try {
            await()
                    .pollDelay(0, SECONDS)
                    .pollInterval(5, SECONDS)
                    .atMost(timeout, SECONDS)
                    .until(() -> doesObjectExist(bucketName, key));
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException(String.format(
                    "Wait %ss. timeout! Object %s doesn't exist in bucket %s.", timeout, key, bucketName), e);
        }
    }*/

    public static void waitForObjects(String bucketName, String prefix, int expectedCount) {
        waitForObjects(bucketName, prefix, expectedCount, 40);
    }

    /**
     * Use when:
     * - waiting for multiple objects
     * - prefix is known (object key is unknown)
     *
     * @param prefix - prefix under which to wait for objects
     */
    public static void waitForObjects(String bucketName, String prefix, int expectedCount, int timeout) {
        /*log.info("Waiting {}s for {} objects in {}/{}...", timeout, expectedCount, bucketName, prefix);
        try {
            await()
                    .pollDelay(0, SECONDS)
                    .pollInterval(5, SECONDS)
                    .atMost(timeout, SECONDS)
                    .until(() -> {
                        int count = S3ObjectRetriever.getKeys(bucketName, prefix).size();
                        log.info("Found {}", count);
                        return count >= expectedCount;
                    });
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException(String.format(
                    "Wait %ss. timeout! Expected %s objects, but found %s in %s/%s",
                    timeout, expectedCount, S3ObjectRetriever.getKeys(bucketName, prefix).size(), bucketName, prefix), e);
        }*/
    }

}
