package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "activity_type_id")
    private ActivityType activityType;

    @Column(name = "activity_value")
    private Double activityValue;

    private String notes;

    private LocalDateTime loggedAt;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public Double getActivityValue() {
        return activityValue;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public void setActivityValue(Double activityValue) {
        this.activityValue = activityValue;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }
}
