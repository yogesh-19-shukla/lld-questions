1) Design summary / best practices (what to say in interview)

Access token (JWT):

Short TTL (e.g. 5–15 minutes).

Contains user claims (sub, roles, iat, exp, jti).

Signed (HS256 or RS256). Verification is stateless.

Refresh token:

Long TTL (days/weeks).

Not a JWT carrying sensitive claims (can be opaque random string or JWT with minimal claims).

Stored server-side (DB or Redis) hashed (store hash(refreshToken)), keyed by userId + deviceId.

Rotation: On each refresh, issue a new refresh token and invalidate the previous one. This prevents token replay.

Reuse detection: If a refresh token that was already revoked is used, treat it as a potential theft and revoke all refresh tokens for that user (force re-login).

Send refresh token as HttpOnly secure cookie (or in body for mobile), never expose in JS.

Logout / revoke:

Delete refresh token from store.

Optionally add access token jti to a short-lived blacklist if immediate revocation is required.

Storage:

Use Redis with TTL for refresh tokens for performance and expiry management.

Store hashed tokens (e.g., SHA-256) — so if DB leaks, plaintext tokens are not revealed.

Note about RS256:

For microservices, RS256 is preferred (private key used for signing, public key for verifying) — facilitates key distribution / rotation.

2) Implementation
Below is a compact, readable implementation using in-memory stores to illustrate the flows:

JwtConfig — configuration & keys

JwtUtil — create & verify access tokens

RefreshTokenStore — server-side store for refresh tokens (in-memory demo; replace with Redis in prod)

AuthService — register/login/refresh/logout flows with rotation & reuse detection

AuthDemo — demo main()

3) How this maps to production

Replace RefreshTokenStore with Redis: use SET key value EX TTL and store hash of token. Use key naming like refresh:{tokenHash} or refresh:user:{userId}:{deviceId}.

Token hashing: We store hash(refreshToken) — keep preimage only in client. If DB leaks, attackers don't get usable tokens.

Rotation & reuse detection: If a refresh token is used twice, it indicates compromise; revoke all tokens for the user and force re-authentication.

Use RS256 for signing: private key signs, services verify with public key — avoids symmetric secret distribution.

Rate limiting: limit refresh attempts & login attempts per IP and per user.

Cookie flags: store refresh token as HttpOnly, Secure, SameSite=Strict cookie for browsers.

Token binding (optional): bind refresh tokens to client/device fingerprint or use PKCE-like flow for mobile.

Logging & monitoring: log suspicious refresh reuse and notify security.