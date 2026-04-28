package com.example.intelligent.study.planner.controller;

import com.example.intelligent.study.planner.service.StudySessionService;
import com.example.intelligent.study.planner.utils.StudySessionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users/{userId}/sessions")
public class StudySessionController {

    private final StudySessionService studySessionService;

    public StudySessionController(StudySessionService studySessionService) {
        this.studySessionService = studySessionService;
    }

    @PostMapping
    public ResponseEntity<?> logSession(
            @PathVariable Long userId,
            @RequestBody StudySessionRequest request
            ){
        request.userId = userId;
        studySessionService.logSession(request);

        return ResponseEntity.ok("session logged");
    }

    @GetMapping
    public ResponseEntity<?> getSessions(@PathVariable Long userID){
        return ResponseEntity.ok(getSessionsByUser(userId));
    }
}
