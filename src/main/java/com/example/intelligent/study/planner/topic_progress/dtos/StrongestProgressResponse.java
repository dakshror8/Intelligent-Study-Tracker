package com.example.intelligent.study.planner.topic_progress;

import java.time.LocalDate;

public record StrongestProgressResponse(Long topicId,
                                        String topicName,
                                        Double avgDifficulty,
                                        Integer timesStudied,
                                        LocalDate lastStudiedDate) {
}
