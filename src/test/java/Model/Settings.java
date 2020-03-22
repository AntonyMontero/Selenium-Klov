package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings {
    private String defaultTestUrl;
    private String businessTestUrl;
    private int defaultWaitTime;
    private int defaultSleepTime;
    private int defaultTimeout;
    private int defaultPollingRate;
    private int maxTestRetry;;
    private String remoteDriverUrl;

    public Settings(){

    }

    public Settings(String defaultTestUrl, String businessTestUrl, int defaultWaitTime, int defaultSleepTime, int defaultTimeout, int defaultPollingRate, int maxTestRetry, String remoteDriverUrl) {
        this.defaultTestUrl = defaultTestUrl;
        this.businessTestUrl = businessTestUrl;
        this.defaultWaitTime = defaultWaitTime;
        this.defaultSleepTime = defaultSleepTime;
        this.defaultTimeout = defaultTimeout;
        this.defaultPollingRate = defaultPollingRate;
        this.maxTestRetry = maxTestRetry;
        this.remoteDriverUrl = remoteDriverUrl;
    }
    public String getDefaultTestUrl() {
        return defaultTestUrl;
    }

    public void setDefaultTestUrl(String defaultTestUrl) {
        this.defaultTestUrl = defaultTestUrl;
    }

    public String getBusinessTestUrl() {
        return businessTestUrl;
    }

    public void setBusinessTestUrl(String businessTestUrl) {
        this.businessTestUrl = businessTestUrl;
    }

    public int getDefaultWaitTime() {
        return defaultWaitTime;
    }

    public void setDefaultWaitTime(int defaultWaitTime) {
        this.defaultWaitTime = defaultWaitTime;
    }

    public int getDefaultSleepTime() {
        return defaultSleepTime;
    }

    public void setDefaultSleepTime(int defaultSleepTime) {
        this.defaultSleepTime = defaultSleepTime;
    }

    public int getMaxTestRetry() {
        return maxTestRetry;
    }

    public void setMaxTestRetry(int maxTestRetry) {
        this.maxTestRetry = maxTestRetry;
    }

    public int getDefaultTimeout() {
        return defaultTimeout;
    }

    public void setDefaultTimeout(int defaultTimeout) {
        this.defaultTimeout = defaultTimeout;
    }

    public int getDefaultPollingRate() {
        return defaultPollingRate;
    }

    public void setDefaultPollingRate(int defaultPollingRate) {
        this.defaultPollingRate = defaultPollingRate;
    }

    public String getRemoteDriverUrl() {
        return remoteDriverUrl;
    }

    public void setRemoteDriverUrl(String remoteDriverUrl) {
        this.remoteDriverUrl = remoteDriverUrl;
    }

}
