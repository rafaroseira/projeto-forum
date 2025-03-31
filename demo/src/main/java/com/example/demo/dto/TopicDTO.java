package com.example.demo.dto;

import java.util.List;
import com.example.demo.model.Topic;

public record TopicDTO(int id, String title, String message, String user, List<CommentDTO> comments) {

    public TopicDTO(Topic topic, List<CommentDTO> commentList){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getUser().getUsername(), commentList);
    }

}
