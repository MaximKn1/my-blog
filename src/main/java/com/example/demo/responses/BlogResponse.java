package com.example.demo.responses;

/**
 * Class used for sending responses to HTTP requests.
 */
public final class BlogResponse {
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAIL = "FAIL";

    public static final String INCORRECT_JSON = "Incorrect JSON formatting";

    private String status;
    private String description;

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(final String responseStatus) {
        this.status = responseStatus;
    }

    public void setDescription(final String responseDescription) {
        this.description = responseDescription;
    }
}
