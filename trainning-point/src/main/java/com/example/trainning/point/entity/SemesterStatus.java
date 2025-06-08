package com.example.trainning.point.entity;

public enum SemesterStatus {
    ACTIVE , EXPIRED , PENDING;
    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean isExpired() {
        return this == EXPIRED;
    }
    public boolean isPending(){
        return this == PENDING;
    }
}
