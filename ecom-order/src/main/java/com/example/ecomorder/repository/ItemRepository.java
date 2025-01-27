package com.example.ecomorder.repository;


import com.example.ecomorder.entity.Item;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends SpannerRepository<Item, String> {
    // Custom query methods (if needed) can be added here
}
