package com.example.demo.service.impl;

import com.example.demo.dto.ActivityLogRequest;
import com.example.demo.entity.ActivityLog;
import com.example.demo.entity.ActivityType;
import com.example.demo.entity.User;
import com.example.demo.repository.ActivityLogRepository;
import com.example.demo.repository.ActivityTypeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ActivityLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;
    private final UserRepository userRepository;
    private final ActivityTypeRepository activityTypeRepository;

    public ActivityLogServiceImpl(ActivityLogRepository activityLogRepository,
                                  UserRepository userRepository,
                                  ActivityTypeRepository activityTypeRepository) {
        this.activityLogRepository = activityLogRepository;
        this.userRepository = userRepository;
        this.activityTypeRepository = activityTypeRepository;
    }

    @Override
    public ActivityLog logActivity(Long userId, Long typeId, ActivityLogRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ActivityType activityType = activityTypeRepository.findById(typeId)
                .orElseThrow(() -> new RuntimeException("Activity type not found"));

        ActivityLog log = new ActivityLog();
        log.setUser(user);
        log.setActivityType(activityType);
        log.setValue(request.getValue());
        log.setNotes(request.getNotes());
        log.setLoggedAt(LocalDateTime.now());

        return activityLogRepository.save(log);
    }

    @Override
    public List<ActivityLog> getLogsByUser(Long userId) {
        return activityLogRepository.findByUserId(userId);
    }

    @Override
    public List<ActivityLog> getLogsByUserAndDateRange(
            Long userId,
            LocalDate startDate,
            LocalDate endDate) {

        return activityLogRepository.findByUserIdAndLoggedAtBetween(
                userId,
                startDate.atStartOfDay(),
                endDate.atTime(23, 59, 59)
        );
    }

    @Override
    public ActivityLog getLogById(Long id) {
        return activityLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity log not found"));
    }
}
