package com.example.intelligent.study.planner.repository;

import com.example.intelligent.study.planner.entity.TopicProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicProgressRepository extends JpaRepository<TopicProgress, Long> {
    List<TopicProgress> findByUserId(Long userId);

    Optional<TopicProgress> findByUserIdAndTopicId(Long userId, Long topicId);
}
