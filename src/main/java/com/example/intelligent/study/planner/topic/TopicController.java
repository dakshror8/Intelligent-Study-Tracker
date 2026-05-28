package com.example.intelligent.study.planner.topic;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/topic")
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public
}
