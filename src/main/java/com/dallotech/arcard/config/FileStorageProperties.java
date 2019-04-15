package com.dallotech.arcard.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    private String profileDir;

    private String qrDir;

    public String getQrDir() {
        return qrDir;
    }

    public void setQrDir(String qrDir) {
        this.qrDir = qrDir;
    }

    public String getProfileDir() {
        return profileDir;
    }

    public void setProfileDir(String profileDir) {
        this.profileDir = profileDir;
    }
}
