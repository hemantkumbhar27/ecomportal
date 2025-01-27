package com.example.ecomorder.repository;

import com.example.ecomorder.entity.User;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends SpannerRepository<User, String> {
    // Custom query methods (if needed) can be added here
}
