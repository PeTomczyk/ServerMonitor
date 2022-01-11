package com.company;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class URLClientTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "http://",
            "google.com",
            "http:/google.com",
            "http:// www.google.com"
    })
    void validateUrl_given_wrongURL_returnFalse(String url) {
        URLClient myClient = new URLClient(url, 0);
        boolean actual = myClient.validateUrl();
        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "http://www.google.com",
            "https://www.bbc.com/",
            "http://localhost:12345/",
            "https://www.google.com?key1=value1&&key2=value2"
    })
    void validateUrl_given_goodURL_returnTrue(String url) {
        URLClient myClient = new URLClient(url, 0);
        boolean actual = myClient.validateUrl();
        assertThat(actual).isTrue();
    }

    @Test
    void getSuccessRate_given_emptyQueue_returnFalse() {
        URLClient myClient = new URLClient("not_important", 0);
        int actual = myClient.getSuccessRate();
        assertThat(actual).isEqualTo(0);
    }
}