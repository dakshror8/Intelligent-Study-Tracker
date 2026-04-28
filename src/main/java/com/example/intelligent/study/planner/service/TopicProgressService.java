package com.example.intelligent.study.planner.service;

import com.example.intelligent.study.planner.entity.TopicProgress;
import com.example.intelligent.study.planner.exceptionHandling.ResourceNotFoundException;
import com.example.intelligent.study.planner.repository.TopicProgressRepository;
import org.jspecify.annotations.Nullable;

import java.util.Comparator;
import java.util.List;

public class TopicProgressService {

    private final TopicProgressRepository progressRepository;

    public TopicProgressService(TopicProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public List<TopicProgress> getUserProgress(Long userId) {
        return progressRepository.findByUserId(userId);
    }

    public TopicProgress getTopicProgress(Long userId, Long topicId) throws ResourceNotFoundException {
        return progressRepository.findByUserIdAndTopicId(userId, topicId)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    // weakest topic => highest avd difficulty
    public TopicProgress getWeakestTopic(Long userId) {
        return progressRepository.findByUserId(userId).stream()
                .max(Comparator.comparing(TopicProgress::getAvgDifficulty))
                .orElse(null);
    }

    // strongest topic => lowest avg difficulty
    public TopicProgress getStrongestTopic(Long userId) {
        return progressRepository.findByUserId(userId).stream()
                .min(Comparator.comparing(TopicProgress::getAvgDifficulty))
                .orElse(null);
    }
}
