package com.example.intelligent.study.planner.recommendation;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommendationResponse {
    private Long topicId;
    private String topicName;
    private String reason;

    public RecommendationResponse(Long topicId, String topicName, String reason) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.reason = reason;
    }

}
