package com.example.demo.dto;

public class ActivityLogRequest {

    private Double value;
    private String notes;

    // getters and setters
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
