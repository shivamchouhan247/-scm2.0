package com.scm.service;

import java.util.Map;
import com.scm.payload.request.RegisterRequest;

public interface UserService {
public Map<String,Object> saveUser(RegisterRequest request);
public Map<String,Object> getUserById(String userId);
}
