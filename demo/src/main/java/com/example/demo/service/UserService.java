package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.CreateUserDTO;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Transactional
    public void createUser(CreateUserDTO dto){

        if(userRepository.existsByEmail(dto.email()) || userRepository.existsByUsername(dto.username()) ){
            throw new UserAlreadyExistsException();
        }

        User user = new User(dto.username(), bcrypt.encode(dto.password()), dto.email());
        userRepository.save(user);
    }
}
