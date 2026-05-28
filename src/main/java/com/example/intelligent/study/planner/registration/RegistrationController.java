package com.example.intelligent.study.planner.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping("/hello")
    public String hello() {
        return "Public Endpoint";
    }

    @PostMapping
    public ResponseEntity<?> register(
            @RequestBody RegistrationRequest request) throws Exception{

        return ResponseEntity.ok(registrationService.register(request));
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
