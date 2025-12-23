package com.example.demo.service.serviceimpl;

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

    private final ActivityLogRepository logRepository;
    private final UserRepository userRepository;
    private final ActivityTypeRepository typeRepository;

    public ActivityLogServiceImpl(ActivityLogRepository logRepository,
                                  UserRepository userRepository,
                                  ActivityTypeRepository typeRepository) {
        this.logRepository = logRepository;
        this.userRepository = userRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public ActivityLog logActivity(Long userId, Long typeId, ActivityLogRequest request) {

        User user = userRepository.findById(userId).orElseThrow();
        ActivityType type = typeRepository.findById(typeId).orElseThrow();

        ActivityLog log = new ActivityLog();
        log.setUser(user);
        log.setActivityType(type);
        log.setActivityValue(request.getValue()); // ✅ FIXED
        log.setNotes(request.getNotes());
        log.setLoggedAt(LocalDateTime.now());

        return logRepository.save(log);
    }

    @Override
    public List<ActivityLog> getLogsByUser(Long userId) {
        return logRepository.findByUserId(userId);
    }

    @Override
    public List<ActivityLog> getLogsByUserAndDateRange(Long userId, LocalDate start, LocalDate end) {
        return logRepository.findByUserIdAndLoggedAtBetween(
                userId,
                start.atStartOfDay(),
                end.atTime(23, 59, 59)
        );
    }

    @Override
    public ActivityLog getLogById(Long id) {
        return logRepository.findById(id).orElseThrow();
    }
}
