package com.omerceyhan.discountservice.service.user;

import com.omerceyhan.discountservice.entity.User;

public interface UserService {
    User findUserById(Long userId);
}
