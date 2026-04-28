package com.example.intelligent.study.planner.service;

import com.example.intelligent.study.planner.entity.User;
import com.example.intelligent.study.planner.exceptionHandling.ResourceAlreadyExistException;
import com.example.intelligent.study.planner.exceptionHandling.ResourceNotFoundException;
import com.example.intelligent.study.planner.exceptionHandling.UserEmailExistException;
import com.example.intelligent.study.planner.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) throws UserEmailExistException {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new UserEmailExistException("email taken");
        }
        userRepository.save(user);
    }


    public void deleteUser(Long userId) throws ResourceNotFoundException {
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new ResourceNotFoundException();
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String email, String name) throws ResourceNotFoundException, ResourceAlreadyExistException, UserEmailExistException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException());

        if(name != null && name.length() > 0 && !Objects.equals(user.getName(), name)){
            user.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)){
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if(userOptional.isPresent()){
                throw new UserEmailExistException("email taken");
            }
            user.setEmail(email);
        }
    }
}
