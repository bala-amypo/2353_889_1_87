package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "activity_type_id")
    private ActivityType activityType;

    private Double value;

    private String notes;

    private LocalDateTime loggedAt;

    // -------- GETTERS --------

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public Double getValue() {
        return value;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    // -------- SETTERS --------

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }
}
