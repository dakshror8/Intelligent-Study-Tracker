package com.example.intelligent.study.planner.study_session;

import com.example.intelligent.study.planner.topic_progress.TopicProgress;
import com.example.intelligent.study.planner.topic_progress.TopicProgressRepository;
import com.example.intelligent.study.planner.topic.Difficulty;
import jakarta.transaction.Transactional;
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

    @Transactional
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

    @Transactional
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

    public List<StudySession> getSessionsByUser(Long userId){
        return studySessionRepository.findByUserId(userId);
    }
}
