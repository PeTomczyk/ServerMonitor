package com.company;

public enum URLStatus {

    HTTP_OK(200, "OK"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int statusCode;
    private final String httpMessage;

    URLStatus(int code, String message) {
        statusCode = code;
        httpMessage = message;
    }

    public static String getStatusMessageForStatusCode(int httpcode) {
        String returnStatusMessage = "Status Not Defined";
        for (URLStatus object : URLStatus.values()) {
            if (object.statusCode == httpcode) {
                returnStatusMessage = object.httpMessage;
            }
        }
        return returnStatusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
