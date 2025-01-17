package com.thebinh.identity_service.service;

import com.thebinh.identity_service.domain.User;
import com.thebinh.identity_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> handleAllUser(){
        return this.userRepository.findAll();
    }

    public void handleSaveUser(User user){
        this.userRepository.save(user);
    }

    public void deleteUserById(long id){
        this.userRepository.deleteById(id);
    }

    public User handleUserById(long id){
        return this.userRepository.findById(id);
    }

    public User handleUserByUsername(String username){
        return this.userRepository.findByUsername(username);
    }

    public void updateUser(User user, User userUpdate){
        user.setDob(userUpdate.getDob());
        user.setFirstName(userUpdate.getFirstName());
        user.setPassword(userUpdate.getPassword());
        user.setUsername(userUpdate.getUsername());
        user.setLastName(userUpdate.getLastName());
    }

}
