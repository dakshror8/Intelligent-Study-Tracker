package com.example.intelligent.study.planner.repository;

import com.example.intelligent.study.planner.entity.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {
    List<StudySession> findByUserId(Long userId);
}
