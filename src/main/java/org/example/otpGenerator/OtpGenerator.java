package org.example.otpGenerator;

import java.security.SecureRandom;

public class OtpGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static String generateOtp(int length) {
        int max = (int) Math.pow(10, length);
        int otp = random.nextInt(max);
        return String.format("%0" + length + "d", otp);
    }

}
