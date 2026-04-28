package com.example.intelligent.study.planner.entity;

import com.example.intelligent.study.planner.utils.Difficulty;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class StudySession {

    @SequenceGenerator(
            name = "session_seq",
            sequenceName = "session_seq",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "session_seq"
    )
    private Long id;
    private Long userId;
    private Long topicId;
    private LocalDate date;
    private Long durationInMinutes;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    public StudySession(Long userId, Long topicId, LocalDate date, Long durationInMinutes, Difficulty difficulty) {
        this.userId = userId;
        this.topicId = topicId;
        this.date = date;
        this.durationInMinutes = durationInMinutes;
        this.difficulty = difficulty;
    }


    @Override
    public String toString() {
        return "StudySession{" +
                "id=" + id +
                ", userId=" + userId +
                ", topicId=" + topicId +
                ", date=" + date +
                ", durationInMinutes=" + durationInMinutes +
                ", difficulty=" + difficulty +
                '}';
    }
}
