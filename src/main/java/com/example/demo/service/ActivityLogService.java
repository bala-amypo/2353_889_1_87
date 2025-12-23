package com.example.demo.service;

import com.example.demo.dto.ActivityLogRequest;
import com.example.demo.entity.ActivityLog;

import java.time.LocalDate;
import java.util.List;

public interface ActivityLogService {

    ActivityLog logActivity(Long userId, Long typeId, ActivityLogRequest request);

    List<ActivityLog> getLogsByUser(Long userId);

    List<ActivityLog> getLogsByUserAndDateRange(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    );

    ActivityLog getLogById(Long id);
}
