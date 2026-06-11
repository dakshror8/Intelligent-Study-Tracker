package com.example.intelligent.study.planner.topic;

import com.example.intelligent.study.planner.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/topic")
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public void createNewTopic(@RequestBody Topic topic){
        topicService.addTopic(topic);
    }

    @GetMapping(path = "{topicId}")
    public Topic getTopic(@PathVariable Long topicId) {
        return topicService.getTopic(topicId);
    }

    @PutMapping(path = "{topicId}")
    public void updateTopic(@PathVariable Long topicId,
                            @RequestParam TopicRequest request){
        topicService.updateTopic(topicId, request);
    }
}
