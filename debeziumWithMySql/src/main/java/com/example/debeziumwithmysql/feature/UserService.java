package com.example.debeziumwithmysql.feature;



import com.example.debeziumwithmysql.feature.dto.UserRequest;
import com.example.debeziumwithmysql.feature.dto.UserResponse;

import java.util.List;

public interface UserService {


    void createUser (UserRequest userRequest);

    List<UserResponse> getAllUser();

    UserResponse getById( Long id);

    UserResponse updateById( Long id , UserRequest userRequest);

    void deleteById( Long id);

}
