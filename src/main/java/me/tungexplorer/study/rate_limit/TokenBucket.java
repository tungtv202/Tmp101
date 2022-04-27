package me.tungexplorer.study.rate_limit;

public class TokenBucket {

    private final long maxBucketSize;
    private final long refillRate;
    private double currentBucketSize;
    private long lastRefillTimestamp;

    public TokenBucket(long maxBucketSize, long refillRate) {
        this.maxBucketSize = maxBucketSize;
        this.refillRate = refillRate;

        currentBucketSize = maxBucketSize;
        lastRefillTimestamp = System.nanoTime();
    }

    public synchronized boolean allowRequest(int tokens) {
        refill();
        if (currentBucketSize > tokens) {
            currentBucketSize -= tokens;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double tokensToAdd = (now - lastRefillTimestamp) * refillRate / 1e9;  // These many tokens accumulated since the last refill
        currentBucketSize = Math.min(currentBucketSize + tokensToAdd, maxBucketSize);  // Number of tokens should never exceed maximum capacity
        lastRefillTimestamp = now;
    }
}
