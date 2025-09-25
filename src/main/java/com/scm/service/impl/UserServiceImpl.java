package com.scm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.exception.ResourceNotFoundException;
import com.scm.payload.request.RegisterRequest;
import com.scm.repo.UserRepository;
import com.scm.service.UserService;
import com.scm.utils.constants.CommonConfigLogic;
import com.scm.utils.constants.ResponseMessage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommonConfigLogic commonConfigLogic;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${default.user.profilepic}")
    private String profilePicUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Map<String, Object> saveUser(RegisterRequest request) {
        Map<String, Object> map = new HashMap<>();
        LOGGER.info("Inside saveUser:");
        try {

            String email = request.getEmail();
            boolean isExists = userRepository.existsByEmail(email);
            if (isExists) {
                return commonConfigLogic.buildResponse(ResponseMessage.CONFLICT, ResponseMessage.STATUS_FAILED,
                        "User is already registered with provided email");
            }

            String userId = UUID.randomUUID().toString();
            LOGGER.info("userId: {}", userId);

            User user = User.builder()
                    .userId(userId)
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .about(request.getAbout())
                    .profilePic(profilePicUrl)
                    .enabled(true)
                    .roles(List.of(ResponseMessage.ROLE_USER))
                    .build();

            User savedUser = userRepository.save(user);
            map = commonConfigLogic.buildResponse(ResponseMessage.SUCCESS, ResponseMessage.STATUS_SUCCESS,
                    ResponseMessage.REGISTERED_SUCCESSFULLY);
            map.put("savedUser", savedUser);

        } catch (Exception e) {
            LOGGER.error("Unexpected error during user registration with email: {}", request.getEmail(), e);
            return commonConfigLogic.buildResponse(ResponseMessage.FAILED, ResponseMessage.STATUS_FAILED,
                    "Something went wrong");

        }

        return map;
    }

    @Override
    public Map<String, Object> getUserById(String userId) {
        LOGGER.info("Fetching user by ID: {}", userId);
        Map<String, Object> response;
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("User not found with the given user id: " + userId));
            response = commonConfigLogic.buildResponse(ResponseMessage.SUCCESS, ResponseMessage.STATUS_SUCCESS,
                    "User found successfully");
            response.put("user", user);

        } catch (ResourceNotFoundException e) {
            LOGGER.warn("User not found with ID: {}", userId, e);
            return commonConfigLogic.buildResponse(ResponseMessage.NOT_FOUND, ResponseMessage.STATUS_FAILED,
                    e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Unexpected Error while fetching user by ID: {}", userId);
            return commonConfigLogic.buildResponse(ResponseMessage.FAILED, ResponseMessage.STATUS_FAILED,
                    "Something went wrong");
        }
        return response;
    }

}
