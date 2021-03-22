package org.autoclouds.aws;

public interface Authentication<T> {
    T createWithDefaultProfile();

    T createWithProfile(String profileName);

    T createWithCredentials(String accessKeyId, String secretAccessKey);
}
