package org.example.jwtAuth;

public class AuthDemo {
    public static void main(String[] args) throws Exception {
        AuthService auth = new AuthService();

        // Login
        AuthService.AuthResponse r = auth.login("alice", "password123", "device-1");
        System.out.println("Access: " + r.accessToken);
        System.out.println("Refresh: " + r.refreshToken);

        // Simulate access token expiry and then refresh
        Thread.sleep(1000);
        AuthService.AuthResponse r2 = auth.refresh(r.refreshToken, "device-1");
        System.out.println("New access: " + r2.accessToken);
        System.out.println("New refresh: " + r2.refreshToken);

        // If old refresh used again -> fails (rotation)
        try {
            auth.refresh(r.refreshToken, "device-1");
        } catch (Exception ex) {
            System.out.println("Expected failure on reusing old refresh token: " + ex.getMessage());
        }

        // Logout (remove refresh)
        auth.logout(r2.refreshToken);
        System.out.println("Logged out.");
    }
}
