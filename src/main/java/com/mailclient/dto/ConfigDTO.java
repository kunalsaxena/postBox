package com.mailclient.dto;

/**
 * Created by kunal on 22/2/17.
 */
public class ConfigDTO {

    private Long gmailLastSyncedEmailNo;

    private String token;

    public Long getGmailLastSyncedEmailNo() {
        return gmailLastSyncedEmailNo;
    }

    public void setGmailLastSyncedEmailNo(Long gmailLastSyncedEmailNo) {
        this.gmailLastSyncedEmailNo = gmailLastSyncedEmailNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ConfigDTO{" +
                "gmailLastSyncedEmailNo=" + gmailLastSyncedEmailNo +
                ", token='" + token + '\'' +
                '}';
    }
}
