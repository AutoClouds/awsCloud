package org.autoclouds.aws.s3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import software.amazon.awssdk.regions.Region;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class S3ClientTests {

    @Test
    @DisplayName("Check client instance creation with default profile")
    void checkClientCreationWithDefaultProfile() {
        AwsS3Client client = AwsS3ClientBuilder.forRegion(Region.US_EAST_1).createWithDefaultProfile();
        assertNotNull(client);
    }

    @ParameterizedTest(name = "Check client cannot be created with {0} as region value")
    @NullAndEmptySource
    void checkErrorIsThrownIfRegionIsNotSpecified(String region) {
        assumingThat(region == null, () -> assertThrows(NullPointerException.class, () -> AwsS3ClientBuilder.forRegion(Region.of(region)).createWithDefaultProfile()));
        assumingThat(region != null, () -> assertThrows(IllegalArgumentException.class, () -> AwsS3ClientBuilder.forRegion(Region.of(region)).createWithDefaultProfile()));
    }

    @Test
    @DisplayName("Check client can be created with non empty region")
    void checkClientIsCreatedIfRegionValueNotEmpty() {
        AwsS3Client client = AwsS3ClientBuilder.forRegion(Region.of("other")).createWithDefaultProfile();
        assertNotNull(client);
    }

    @ParameterizedTest(name = "Check client can be created with {0} as profile name")
    @NullAndEmptySource
    @ValueSource(strings = {"other"})
    void checkClientCreationWithAnyProfileValue(String profileName) {
        AwsS3Client client = AwsS3ClientBuilder.forRegion(Region.US_EAST_1).createWithProfile(profileName);
        assertNotNull(client);
    }

    @ParameterizedTest(name = "Check error is thrown if Access and/or Secret keys is equal to {0}")
    @CsvSource({
            ",,Access key ID cannot be blank.",
            "1,,Secret access key cannot be blank.",
            "'','',Access key ID cannot be blank.",
            "1,'',Secret access key cannot be blank."
    })
    void checkErrorIsThrownWhenAccessAndOrSecretKeysNotSpecified(String accessKey, String secretKey, String errorMessage) {
        Exception exception = assertThrows(NullPointerException.class, () -> AwsS3ClientBuilder.forRegion(Region.US_EAST_1).createWithCredentials(accessKey, secretKey));
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Check client can be created if both Access and Secret access keys is specified")
    void checkClientCanBeCreatedIfBothKeysIsSpecified() {
        AwsS3Client client = AwsS3ClientBuilder.forRegion(Region.US_EAST_1).createWithCredentials("1", "1");
        assertNotNull(client);
    }
}
