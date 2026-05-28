package com.example.intelligent.study.planner.topic_progress;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TopicProgress {

    @SequenceGenerator(
            name = "progress_seq",
            sequenceName = "progress_seq",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "progress_seq"
    )
    private Long id;
    private Long userId;
    private Long topicId;
    private Integer timesStudied;
    private Double avgDifficulty;
    private LocalDate lastStudiedDate;


}
