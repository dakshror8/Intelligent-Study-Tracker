package com.example.intelligent.study.planner.study_session;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/sessions")
public class StudySessionController {

    private final StudySessionService studySessionService;

    public StudySessionController(StudySessionService studySessionService) {
        this.studySessionService = studySessionService;
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<?> logSession(
            @PathVariable Long userId,
            @RequestBody StudySessionRequest request
            ){
        request.userId = userId;
        studySessionService.logSession(request);

        return ResponseEntity.ok("session logged");
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<?> getSessions(@PathVariable Long userId){
        return ResponseEntity.ok(studySessionService.getSessionsByUser(userId));
    }
}
