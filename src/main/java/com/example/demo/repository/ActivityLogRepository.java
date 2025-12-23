import java.time.LocalDateTime;
import java.util.List;

public interface ActivityLogRepository
        extends JpaRepository<ActivityLog, Long> {

    List<ActivityLog> findByUserId(Long userId);

    List<ActivityLog> findByUserIdAndLoggedAtBetween(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );
}
