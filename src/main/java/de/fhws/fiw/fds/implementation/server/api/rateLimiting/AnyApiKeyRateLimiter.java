package de.fhws.fiw.fds.implementation.server.api.rateLimiting;

import de.fhws.fiw.fds.sutton.server.api.rateLimiting.RateLimiter;

public class AnyApiKeyRateLimiter extends RateLimiter {

    public AnyApiKeyRateLimiter() {
        super(0);
    }

    @Override
    public boolean isRequestAllowed(String apiKey) {
        return true;
    }
}
