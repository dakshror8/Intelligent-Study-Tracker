package com.example.intelligent.study.planner.topic_progress;

import java.time.LocalDate;

public record TopicProgressResponse(
        Long topicId,
        String topicName,
        Double avgDifficulty,
        Integer timesStudied,
        LocalDate lastStudiedDate
) {
}
