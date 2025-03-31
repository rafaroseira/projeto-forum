package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.BasicTopicInfoDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.CreateTopicDTO;
import com.example.demo.dto.TopicDTO;
import com.example.demo.dto.TopicPreviewDTO;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.TopicNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Comment;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TopicRepository;
import com.example.demo.repository.UserRepository;

@Service
public class TopicService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void createTopic(CreateTopicDTO dto){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());

        if(user == null){
            throw new UserNotFoundException();
        }

        Category category = categoryRepository.findById((byte)dto.categoryId());

        if(category == null){throw new CategoryNotFoundException();}

        topicRepository.save(
            new Topic(dto.title(), dto.message(), category, user, null)
        );
    }

    @Transactional
    public TopicDTO getTopic(int id){

        Topic topic = topicRepository.findById(id);

        if(topic == null){throw new TopicNotFoundException();}

        List<CommentDTO> comments = new ArrayList<CommentDTO>();

        for(Comment comment : topic.getComments()){
            comments.add(new CommentDTO(comment));
        }

        return new TopicDTO(topic, comments);
    }

    @Transactional
    public void deleteTopic(int id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Topic topic = topicRepository.findById(id);

        if(topic == null){throw new TopicNotFoundException();}

        if(topic.getUser().getUsername() != auth.getName()){throw new TopicNotFoundException("Tópico pertence a outro usuário!");}

        topicRepository.delete(topic);
    }

    @Transactional
    public List<TopicPreviewDTO> getPreviews(int categoryId){
        return topicRepository.findAllByCategoryId((byte)categoryId);
    }

    @Transactional
    public List<BasicTopicInfoDTO> getMyTopics(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());

        if(user == null){throw new UserNotFoundException();}

        if(user.getTopics() == null){throw new NullPointerException("Você não possui nenhum tópico!");}

        List<BasicTopicInfoDTO> topicsInfo = new ArrayList<BasicTopicInfoDTO>();
        
        for(Topic topic : user.getTopics()){
            topicsInfo.add(new BasicTopicInfoDTO(topic.getId(), topic.getTitle(), topic.getCategory().getName()));
        }
        
        return topicsInfo;
    }
}
