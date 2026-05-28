package com.example.intelligent.study.planner.recommendation;

import com.example.intelligent.study.planner.topic.Topic;
import com.example.intelligent.study.planner.topic_progress.TopicProgress;
import com.example.intelligent.study.planner.topic_progress.TopicProgressRepository;
import com.example.intelligent.study.planner.topic.TopicRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;


@Service
public class RecommendationService {

    private final TopicProgressRepository progressRepository;
    private final TopicRepository topicRepo;

    public RecommendationService(TopicProgressRepository progressRepository, TopicRepository topicRepo) {
        this.progressRepository = progressRepository;
        this.topicRepo = topicRepo;
    }

    public RecommendationResponse recommendNext(Long userId) {

        List<Topic> allTopics = topicRepo.findAll();
        List<TopicProgress> progressList = progressRepository.findByUserId(userId);

        // ✅ Step 1: Find unstudied topics
        Set<Long> studiedIds = progressList.stream()
                .map(TopicProgress::getTopicId)
                .collect(Collectors.toSet());

        List<Topic> unstudied = allTopics.stream()
                .filter(t -> !studiedIds.contains(t.getId()))
                .toList();

        // 🔥 New topic priority
        if (!unstudied.isEmpty()) {
            Topic random = unstudied.get(new Random().nextInt(unstudied.size()));

            return new RecommendationResponse(
                    random.getId(),
                    random.getName(),
                    "New topic to explore"
            );
        }

        // ✅ Step 2: Score existing topics
        TopicProgress best = null;
        double maxScore = -1;

        for (TopicProgress tp : progressList) {

            long days = DAYS.between(
                    tp.getLastStudiedDate(),
                    LocalDate.now()
            );

            days = Math.min(days, 7); // recency cap

            double score = (days * 2)
                        + (tp.getAvgDifficulty() * 3)
                        - (tp.getTimesStudied());

            if (score > maxScore) {
                maxScore = score;
                best = tp;
            }
        }

        // ✅ Step 3: Build response
        Topic topic = topicRepo.findById(best.getTopicId())
                .orElseThrow();

        return new RecommendationResponse(
                topic.getId(),
                topic.getName(),
                buildReason(best)
        );
    }

    // 🧠 Reason logic (core intelligence exposed as text)
    private String buildReason(TopicProgress tp) {

        if (tp.getAvgDifficulty() >= 2.5) {
            return "High difficulty - needs improvement";
        }

        if (tp.getTimesStudied() < 2) {
            return "Low exposure - needs more practice";
        }

        long days = DAYS.between(tp.getLastStudiedDate(), LocalDate.now());

        if (days >= 3) {
            return "Not studied recently - revision needed";
        }

        return "Recommended for balanced practice";
    }
}
