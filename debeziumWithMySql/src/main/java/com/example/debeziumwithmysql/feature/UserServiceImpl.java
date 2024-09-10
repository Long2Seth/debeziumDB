package com.example.debeziumwithmysql.feature;



import com.example.debeziumwithmysql.domain.User;
import com.example.debeziumwithmysql.feature.dto.UserRequest;
import com.example.debeziumwithmysql.feature.dto.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.debezium.data.Envelope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Override
    public void createUser(UserRequest userRequest) {

        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPhoneNumber(userRequest.phoneNumber());
        user.setAddress(userRequest.address());
        
        userRepository.save(user);
        
    }

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getAddress()
                )).collect(Collectors.toList());
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

    public void replicateData(Map<String, Object> customerData, Envelope.Operation operation) {
        final ObjectMapper mapper = new ObjectMapper();
        final User customer = mapToCustomer(customerData);
        System.out.println(customer);
        if (Envelope.Operation.DELETE == operation) {
            System.out.println("delete the record");
            userRepository.deleteById(customer.getId());
        } else {
            System.out.println("inserting the record");
            userRepository.save(customer);
        }
    }

    public static User mapToCustomer(Map<String, Object> map) {
        User customer = new User();

        if (map.containsKey("id") && map.get("id") instanceof Number) {
            customer.setId(((Number) map.get("id")).longValue());
        }

        if (map.containsKey("full_name") && map.get("full_name") instanceof String) {
            customer.setName((String) map.get("name"));
        }

        if (map.containsKey("email") && map.get("email") instanceof String) {
            customer.setEmail((String) map.get("email"));
        }

        return customer;
    }
}
