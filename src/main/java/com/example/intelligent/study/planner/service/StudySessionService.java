package com.example.intelligent.study.planner.service;

import com.example.intelligent.study.planner.entity.StudySession;
import com.example.intelligent.study.planner.entity.TopicProgress;
import com.example.intelligent.study.planner.repository.StudySessionRepository;
import com.example.intelligent.study.planner.repository.TopicProgressRepository;
import com.example.intelligent.study.planner.utils.Difficulty;
import com.example.intelligent.study.planner.utils.StudySessionRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudySessionService {

    private final StudySessionRepository studySessionRepository;
    private final TopicProgressRepository progressRepository;

    public StudySessionService(StudySessionRepository studySessionRepository,
                               TopicProgressRepository progressRepository) {
        this.studySessionRepository = studySessionRepository;
        this.progressRepository = progressRepository;
    }

    public void logSession(StudySessionRequest request) {

        // Save session
        StudySession session = new StudySession();
        session.setUserId(request.userId);
        session.setTopicId(request.topicId);
        session.setDate(LocalDate.now());
        session.setDurationInMinutes(request.durationInMinutes);
        session.setDifficulty(request.difficulty);

        studySessionRepository.save(session);

        // Update TopicProgress
        updateTopicProgress(request);
    }

    private void updateTopicProgress(StudySessionRequest request) {

        TopicProgress progress = progressRepository
                .findByUserIdAndTopicId(request.userId, request.topicId)
                .orElse(new TopicProgress());

        if(progress.getId() == null){
            progress.setUserId(request.userId);
            progress.setTopicId(request.topicId);
            progress.setTimesStudied(0);
            progress.setAvgDifficulty(0.0);
        }

        int newScore = mapDifficulty(request.difficulty);

        int times = progress.getTimesStudied();
        double oldAvg = progress.getAvgDifficulty();

        double newAvg = ((oldAvg * times) + newScore) / (times+1);

        progress.setTimesStudied(times+1);
        progress.setAvgDifficulty(newAvg);
        progress.setLastStudiedDate(LocalDate.now());

        progressRepository.save(progress);
    }

    private int mapDifficulty(Difficulty diff){
        return switch(diff) {
            case EASY -> 1;
            case MEDIUM -> 2;
            case HARD -> 3;
        };
    }

    private List<StudySession> getSessionsByUser(Long userId){
        return studySessionRepository.findByUserId(userId);
    }
}
