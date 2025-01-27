package com.example.ecomorder.repository;


import com.example.ecomorder.entity.Bag;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagRepository extends SpannerRepository<Bag, String> {
    // Custom query methods (if needed) can be added here
}
