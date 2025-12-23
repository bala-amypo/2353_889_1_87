package com.example.demo.controller;

import com.example.demo.dto.ActivityLogRequest;
import com.example.demo.entity.ActivityLog;
import com.example.demo.service.ActivityLogService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class ActivityLogController {

    private final ActivityLogService logService;

    public ActivityLogController(ActivityLogService logService) {
        this.logService = logService;
    }

    /**
     * POST /api/logs/{userId}/{typeId}
     * Log an activity for a user
     */
    @PostMapping("/{userId}/{typeId}")
    public ActivityLog logActivity(
            @PathVariable Long userId,
            @PathVariable Long typeId,
            @RequestBody ActivityLogRequest request) {

        return logService.logActivity(userId, typeId, request);
    }

    /**
     * GET /api/logs/user/{userId}
     * Get all activity logs for a user
     */
    @GetMapping("/user/{userId}")
    public List<ActivityLog> getLogsByUser(@PathVariable Long userId) {
        return logService.getLogsByUser(userId);
    }

    /**
     * GET /api/logs/user/{userId}/range?startDate=2025-01-01&endDate=2025-12-31
     * Get logs by date range
     */
    @GetMapping("/user/{userId}/range")
    public List<ActivityLog> getLogsByDateRange(
            @PathVariable Long userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return logService.getLogsByUserAndDateRange(userId, startDate, endDate);
    }

    /**
     * GET /api/logs/{id}
     * Get single activity log by id
     */
    @GetMapping("/{id}")
    public ActivityLog getLogById(@PathVariable Long id) {
        return logService.getLogById(id);
    }
}
