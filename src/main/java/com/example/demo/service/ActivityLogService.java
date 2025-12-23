package com.example.demo.service;

import com.example.demo.dto.ActivityLogRequest;
import com.example.demo.entity.ActivityLog;

import java.time.LocalDate;
import java.util.List;

public interface ActivityLogService {

    // POST /api/logs/{userId}/{typeId}
    ActivityLog logActivity(Long userId, Long typeId, ActivityLogRequest request);

    // GET /api/logs/user/{userId}
    List<ActivityLog> getLogsByUser(Long userId);

    // GET /api/logs/user/{userId}/range
    List<ActivityLog> getLogsByUserAndDateRange(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    );

    // GET /api/logs/{id}
    ActivityLog getLogById(Long id);
}
