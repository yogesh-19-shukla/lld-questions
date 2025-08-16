package org.example.otpGenerator;

import java.time.LocalDateTime;

public class OtpService {

    private final OtpStore store = new OtpStore();
    private static final int OTP_LENGTH = 6;
    private static final int EXPIRY_MINUTES = 5;

    public String generateOtpForUser(String userId) {
        String code = OtpGenerator.generateOtp(OTP_LENGTH);
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(EXPIRY_MINUTES);
        OTP otp = new OTP(userId, code, expiryTime);
        store.saveOtp(userId, otp);
        return code;
    }

    public boolean validateOtp(String userId, String enteredOtp) {
        OTP otp = store.getOtp(userId);
        if (otp == null) return false;
        if (otp.isExpired()) {
            store.removeOtp(userId);
            return false;
        }
        boolean isValid = otp.getCode().equals(enteredOtp);
        if (isValid)    store.removeOtp(userId);
        return isValid;
    }
}
