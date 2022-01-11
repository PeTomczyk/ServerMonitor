package com.company;

import com.google.common.collect.EvictingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLClient {

    private static final Logger log = LoggerFactory.getLogger(URLClient.class);
    private final String urlString;
    HttpURLConnection connection;
    EvictingQueue<Integer> queue;
    private URL url;

    public URLClient(String inputURL, Integer queueSize) {
        this.urlString = inputURL;
        this.url = null;
        this.connection = null;
        this.queue = EvictingQueue.create(queueSize);
    }

    public boolean validateUrl() {
        if (verifyUrl(this.urlString)) {
            try {
                this.url = new URL(this.urlString);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    public void checkUrl() {
        if (this.url != null) {
            try {
                connection = (HttpURLConnection) url.openConnection();
                if (connection.getResponseCode() == URLStatus.HTTP_OK.getStatusCode()) {
                    queue.add(1);
                    // log.info("Success: " + URLStatus.getStatusMessageForStatusCode(connection.getResponseCode()), URLClient.class.getSimpleName());
                } else {
                    queue.add(0);
                    // log.warn("Failure: " + URLStatus.getStatusMessageForStatusCode(connection.getResponseCode()), URLClient.class.getSimpleName());
                }
            } catch (Exception e) {
                queue.add(0);
                log.error("Connection unreachable.", URLClient.class.getSimpleName());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
    }

    private boolean verifyUrl(String url) {
        String urlRegex = "^(http|https)://[-a-zA-Z0-9+&@#/%?=~_|,!:.;]*[-a-zA-Z0-9+@#/%=&_|]";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher m = pattern.matcher(url);
        return m.matches();
    }

    public int getSuccessRate() {
        if (queue.isEmpty()) return 0;
        int sumSuccess = queue.stream().mapToInt(Integer::intValue).sum();
        return (int) ((sumSuccess * 100.0f) / (queue.size()));
    }
}
