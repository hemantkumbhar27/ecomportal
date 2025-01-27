package com.example.ecomorder.controller;

import com.example.ecomorder.entity.Item;
import com.example.ecomorder.repository.BagRepository;
import com.example.ecomorder.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserController {

    @PostConstruct
    public void init() {

        Item item   = new Item();
        item.setItemId("Item");
    }
}
