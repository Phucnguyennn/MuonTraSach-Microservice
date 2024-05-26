package com.test.notificationserver;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private RateLimiter rateLimiter = RateLimiter.create(0.2); // Giới hạn 2 yêu cầu mỗi 10 giây

    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }
}