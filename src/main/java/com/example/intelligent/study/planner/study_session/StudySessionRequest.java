package com.example.intelligent.study.planner.study_session;


import com.example.intelligent.study.planner.topic.Difficulty;

public class StudySessionRequest {
    public Long userId;
    public Long topicId;
    public Long durationInMinutes;
    public Difficulty difficulty;
}
