package com.example.debeziumwithpostgres.feature;


import com.example.debeziumwithpostgres.domain.User;
import com.example.debeziumwithpostgres.feature.dto.UserRequest;
import com.example.debeziumwithpostgres.feature.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Override
    public void createUser(UserRequest userRequest) {

        User user = new User();
        userRepository.save(user);
        
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse getById(Long id) {
        return null;
    }

    @Override
    public UserResponse updateById(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

}
