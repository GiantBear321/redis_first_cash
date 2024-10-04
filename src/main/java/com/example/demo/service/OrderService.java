package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public Integer save(Order order) {
        return repository.save(order).getId();
    }


    @Cacheable(value = "order")
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Cacheable(value = "order", key = "#id")
    public Order findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Caching(
            evict = {@CacheEvict(value = "order", allEntries = true), @CacheEvict(value = "order", key = "#id")}
    )
    public void deleteOrderById(Integer id) {
        repository.deleteById(id);
    }
}
