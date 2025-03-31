package com.example.demo.dto;

import com.example.demo.model.Topic;

public record BasicTopicInfoDTO(int id, String title, String category) {

    public BasicTopicInfoDTO(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getCategory().getName());
    }
}
