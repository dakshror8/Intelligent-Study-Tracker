package com.example.intelligent.study.planner.controller;

import com.example.intelligent.study.planner.exceptionHandling.ResourceNotFoundException;
import com.example.intelligent.study.planner.service.TopicProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users/{userId}/progress")
public class TopicProgressController {

    private final TopicProgressService service;

    public TopicProgressController(TopicProgressService service) {
        this.service = service;
    }

    // ✅ All topics progress
    @GetMapping
    public ResponseEntity<?> getAll(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getUserProgress(userId));
    }

    // ✅ Specific topic progress
    @GetMapping("/{topicId}")
    public ResponseEntity<?> getTopic(
            @PathVariable Long userId,
            @PathVariable Long topicId) throws ResourceNotFoundException {

        return ResponseEntity.ok(
                service.getTopicProgress(userId, topicId)
        );
    }

    // ✅ Weakest topic
    @GetMapping("/weakest")
    public ResponseEntity<?> weakest(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getWeakestTopic(userId));
    }

    // ✅ Strongest topic
    @GetMapping("/strongest")
    public ResponseEntity<?> strongest(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getStrongestTopic(userId));
    }
}
