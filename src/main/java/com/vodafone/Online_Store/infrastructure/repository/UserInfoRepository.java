package com.vodafone.Online_Store.infrastructure.repository;

import com.vodafone.Online_Store.core.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    // Additional custom queries if needed
    Optional<UserInfo> findByUsername(String username);

}