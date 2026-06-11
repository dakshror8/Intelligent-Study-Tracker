package com.example.intelligent.study.planner.topic;

import com.example.intelligent.study.planner.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public void addTopic(Topic topic) {
        Optional<Topic> topicOptional = topicRepository.findById(topic.getId());

        if (topicOptional.isPresent()) {
            throw new IllegalStateException("topic already exist.");
        }

        topicRepository.save(topic);
    }

    public Topic getTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("topic not found with id: " + topicId));
        return topic;
    }


    public void updateTopic(Long topicId, TopicRequest request) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("topic not found with id: " + topicId));

        topic.setName(request.name);
        topic.setDescription(request.description);
        topicRepository.save(topic);
    }
}
