package com.alfacloud.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="polar")
public class PolarProperties {
    /**
     * Welcome message for new user
     */
    private String greeting;
    /**
     * Property to load Test Data during local development or testing
     */
    private boolean enabledTestData;

    public String getGreeting() {return greeting;}
    public void setGreeting(String greeting) {this.greeting = greeting;}
    public boolean getEnableTestData() {return enabledTestData;}
    public void setEnableTestData(boolean enabledTestData) {this.enabledTestData = enabledTestData;}
}
