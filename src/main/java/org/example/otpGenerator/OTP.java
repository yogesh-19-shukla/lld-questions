package org.example.otpGenerator;

import java.time.LocalDateTime;

public class OTP {
    private final String userID;
    private final String code;
    private final LocalDateTime expiryTime;

    public OTP(String userID, String code, LocalDateTime expiryTime) {
        this.userID = userID;
        this.code = code;
        this.expiryTime = expiryTime;
    }

    public String getUserID() {
        return userID;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryTime);
    }
}
