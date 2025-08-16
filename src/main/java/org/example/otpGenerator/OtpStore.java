package org.example.otpGenerator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OtpStore {
    private final Map<String, OTP> otpMap = new ConcurrentHashMap<>();

    public void saveOtp(String userId, OTP otp) {
        otpMap.put(userId, otp);
    }

    public OTP getOtp(String userId) {
        return otpMap.get(userId);
    }

    public void removeOtp(String userId) {
        otpMap.remove(userId);
    }
}
