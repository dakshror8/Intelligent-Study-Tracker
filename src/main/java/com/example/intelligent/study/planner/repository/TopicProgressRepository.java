package com.example.intelligent.study.planner.repository;

import com.example.intelligent.study.planner.model.TopicProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicProgressRepository extends JpaRepository<TopicProgress, Long> {
    List<TopicProgress> findByUserId(Long userId);

    Optional<TopicProgress> findByUserIdAndTopicId(Long userId, Long topicId);
}
