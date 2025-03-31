package com.example.demo.dto;

import com.example.demo.model.Comment;

public record CommentDTO(int id, String message, String user) {

    public CommentDTO(Comment comment) {
        this(comment.getId(), comment.getMessage(), comment.getUser().getUsername());
    }
}

