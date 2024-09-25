package com.example.hospital_accompany.service;

import com.example.hospital_accompany.dto.CustomUserDetails;
import com.example.hospital_accompany.entity.User;
import com.example.hospital_accompany.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //DB에서 조회
        User userData = userRepository.findByUsername(username)
                .orElseThrow();

        // 오류 처리 구현
        if (userData == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(userData);
    }
}
