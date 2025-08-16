package org.example.otpGenerator;

public class Demo {

    public static void main(String[] args) {
        OtpService service = new OtpService();

        String userId = "user123";
        String otp = service.generateOtpForUser(userId);
        System.out.println("Generated OTP for " + userId + ": " + otp);

        // ✅ correct validation
        boolean result = service.validateOtp(userId, otp);
        System.out.println("Validation result (correct): " + result);

        // ❌ wrong OTP
        result = service.validateOtp(userId, "123456");
        System.out.println("Validation result (wrong): " + result);
    }
}
