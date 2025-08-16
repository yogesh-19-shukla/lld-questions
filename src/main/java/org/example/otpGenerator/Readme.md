Otp → model class for OTP details (code, expiry, user).

OtpGenerator → generates a secure 6-digit OTP.

OtpStore → stores OTPs temporarily (in-memory for demo, but could be Redis/DB in real world).

OtpService → main service that handles creation, validation, and expiration.

Demo/Main → to test.


########################################################################################3


Security: Uses SecureRandom, stores only salted hashes, constant-time compare.

Correctness: Enforces TTL, attempt limits, resend cooldown, one-time use.

Thread-safety: Per-user ReentrantLock; safe under concurrent sends/verifications.

Extensibility: Pluggable Notifier, configurable digits/ttl/attempts/cooldown. You can easily swap to SMS/Email gateways.

Scalability (talk-track):

In production, move OtpStore to Redis with TTL keys.

Use rate limiting (e.g., token bucket per user & IP).

For distributed concurrency, rely on atomic set-if-absent operations or Redlock.

Use idempotency keys if your “send OTP” endpoint can be retried.