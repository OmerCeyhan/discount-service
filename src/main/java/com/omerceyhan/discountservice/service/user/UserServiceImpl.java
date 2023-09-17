package com.omerceyhan.discountservice.service.user;

import com.omerceyhan.discountservice.entity.User;
import com.omerceyhan.discountservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
