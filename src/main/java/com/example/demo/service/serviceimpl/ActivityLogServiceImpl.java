package com.example.demo.service.impl;

import com.example.demo.entity.ActivityLog;
import com.example.demo.entity.ActivityType;
import com.example.demo.entity.User;
import com.example.demo.repository.ActivityLogRepository;
import com.example.demo.repository.ActivityTypeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ActivityLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository logRepository;
    private final UserRepository userRepository;
    private final ActivityTypeRepository typeRepository;

    public ActivityLogServiceImpl(
            ActivityLogRepository logRepository,
            UserRepository userRepository,
            ActivityTypeRepository typeRepository
    ) {
        this.logRepository = logRepository;
        this.userRepository = userRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public ActivityLog logActivity(Long userId, Long activityTypeId, ActivityLog log) {

        User user = userRepository.findById(userId).orElseThrow();
        ActivityType type = typeRepository.findById(activityTypeId).orElseThrow();

        log.setUser(user);
        log.setActivityType(type);

        return logRepository.save(log);
    }

    @Override
    public List<ActivityLog> getLogsByUser(Long userId) {
        return logRepository.findByUserId(userId);
    }

    @Override
    public List<ActivityLog> getLogsByUserAndDate(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return logRepository.findByUserIdAndActivityDateBetween(
                userId, startDate, endDate
        );
    }
}
