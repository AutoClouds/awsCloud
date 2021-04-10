package org.autoclouds.aws.s3.parameters;

public class CopyParameters {
    private String destinationBucket = null;
    private String sourceKey = null;
    private String destinationKey = null;

    public String getDestinationBucket() {
        return destinationBucket;
    }

    public CopyParameters setDestinationBucket(String destinationBucket) {
        this.destinationBucket = destinationBucket;
        return this;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public CopyParameters setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
        return this;
    }

    public String getDestinationKey() {
        return destinationKey != null ? destinationKey : sourceKey;
    }

    public CopyParameters setDestinationKey(String destinationKey) {
        this.destinationKey = destinationKey;
        return this;
    }
}
