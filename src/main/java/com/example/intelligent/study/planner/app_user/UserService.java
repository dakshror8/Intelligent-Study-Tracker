package com.example.intelligent.study.planner.app_user;

import com.example.intelligent.study.planner.exception.ResourceAlreadyExistException;
import com.example.intelligent.study.planner.exception.ResourceNotFoundException;
import com.example.intelligent.study.planner.exception.UserEmailExistException;
import com.example.intelligent.study.planner.registration.token.ConfirmationToken;
import com.example.intelligent.study.planner.registration.token.ConfirmationTokenService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return org.springframework.security.core.userdetails.User.builder()
//                .username("loki")
//                .password(
//                        new BCryptPasswordEncoder()
//                                .encode("1234")
//                )
//                .roles("USER")
//                .build();
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public String signUpUser(User user) throws ResourceAlreadyExistException {
        boolean exists = userRepository.findUserByEmail(user.getEmail()).isPresent();

        if(exists){
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        // TODO: Send Confirmation token

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO: SEND EMAIL

        return token;
    }

    public int enableAppUser(String email) {
        return userRepository.enableUser(email);
    }
}
