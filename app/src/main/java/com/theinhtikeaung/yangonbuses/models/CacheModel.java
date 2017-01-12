package com.theinhtikeaung.yangonbuses.models;

import java.io.Serializable;

/**
 * Created by pasca on 12/9/16.
 */
public class CacheModel implements Serializable {
    private static final long serialVersionUID = 8448089304580549700L;
    private String response;
    private long expiryStamp;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public long getExpiryStamp() {
        return expiryStamp;
    }

    public void setExpiryStamp(long expiryStamp) {
        this.expiryStamp = expiryStamp;
    }
}
