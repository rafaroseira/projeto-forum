package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.dto.TopicPreviewDTO;
import com.example.demo.model.Topic;
import com.example.demo.model.User;

public interface TopicRepository extends JpaRepository<Topic,Integer> {

    @Query("select new com.example.demo.dto.TopicPreviewDTO(t.id, t.title, u.username) from Topic t join t.user u where t.category.id=:categoryId")
    List<TopicPreviewDTO> findAllByCategoryId(@Param("categoryId") byte categoryId);

    List<Topic> findByUser(User user);

    Topic findById(int id);
}
