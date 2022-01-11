package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    private static final long POOLING_RATE_MS = 1000; // 1000 ms => 1/sec => 60 times/minute
    private static final Integer RESULT_SET_SIZE = 60 * 5;  // 60 times/minute * 5 min => 300 samples in 5 minutes
    private static final String FILE_PATH = "url.txt";
    private static final String FALLBACK_URL = "http://localhost:12345/";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String url = getUrl();
        URLClient myClient = new URLClient(url, RESULT_SET_SIZE);

        if (myClient.validateUrl()) {
            log.info("Checking url: " + url, Main.class.getSimpleName());
            while (true) {
                try {
                    myClient.checkUrl();
                    logResult(myClient.getSuccessRate());
                    Thread.sleep(POOLING_RATE_MS);
                } catch (Exception e) {
                    log.error("Failed to check URL: " + e.getMessage(), Main.class.getSimpleName());
                }
            }
        } else {
            log.error("URL is invalid.", Main.class.getSimpleName());
        }
    }

    private static void logResult(Integer successRate) {
        log.info("Result: " + successRate + "% of success.", Main.class.getSimpleName());
        if (successRate == 0) {
            log.error("Result is 0. There might be a problem with Server.", Main.class.getSimpleName());
        }
    }

    private static String getUrl() {
        String url = null;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            url = br.readLine();
        } catch (Exception e) {
            log.warn("Missing url config file: " + FILE_PATH, Main.class.getSimpleName());
        }

        if (url == null) {
            url = FALLBACK_URL;
            log.warn("Url is empty. Falling back to default: " + FALLBACK_URL, Main.class.getSimpleName());
        }
        return url;
    }
}
