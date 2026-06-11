package com.example.intelligent.study.planner.topic_progress;

import com.example.intelligent.study.planner.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TopicProgressService {

    private final TopicProgressRepository progressRepository;

    public TopicProgressService(TopicProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public List<TopicProgress> getUserProgress(Long userId) {
        return progressRepository.findByUserId(userId);
    }

    public TopicProgress getTopicProgress(Long userId, Long topicId) {
        return progressRepository.findByUserIdAndTopicId(userId, topicId)
                .orElseThrow(() -> new ResourceNotFoundException("Topic Progress not found"));
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
