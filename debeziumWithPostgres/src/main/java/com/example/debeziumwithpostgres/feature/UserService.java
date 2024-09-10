package com.example.debeziumwithpostgres.feature;



import com.example.debeziumwithpostgres.domain.User;
import com.example.debeziumwithpostgres.feature.dto.UserRequest;
import com.example.debeziumwithpostgres.feature.dto.UserResponse;


import java.util.List;

public interface UserService {


    void createUser (UserRequest userRequest);

    List<User> getAllUser();

    UserResponse getById(Long id);

    UserResponse updateById( Long id , UserRequest userRequest);

    void deleteById( Long id);

}
