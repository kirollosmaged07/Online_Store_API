package com.vodafone.Online_Store.core.service;

import com.vodafone.Online_Store.core.domain.UserInfo;
import com.vodafone.Online_Store.infrastructure.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public String registerUser(UserInfo user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userInfoRepository.save(user);
        String role = user.getRole();
        String jwtToken = jwtService.generateTokenWithRoles(user.getUsername(), role);
        return jwtToken;
    }

    // Other service methods
}
