package com.example.intelligent.study.planner.utils.request;


import com.example.intelligent.study.planner.model.Difficulty;

public class StudySessionRequest {
    public Long userId;
    public Long topicId;
    public Long durationInMinutes;
    public Difficulty difficulty;
}
