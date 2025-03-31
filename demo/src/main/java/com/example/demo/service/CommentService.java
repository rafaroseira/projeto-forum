package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.PostCommentDTO;
import com.example.demo.exceptions.TopicNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TopicRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void postComment(PostCommentDTO dto){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Topic topic = topicRepository.findById(dto.topicId());

        if(topic == null){throw new TopicNotFoundException();}

        User user = userRepository.findByUsername(auth.getName());

        if(user == null){throw new UserNotFoundException();}

        Comment comment = new Comment(dto.message(), user, topic);
        commentRepository.save(comment);
    }
}
