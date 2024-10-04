package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<Integer> saveOrder(@RequestBody Order order) {
        return new ResponseEntity<>(service.save(order), HttpStatusCode.valueOf(201));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(service.findAll(), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<Order> getOrderById(@RequestParam Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOrderById(@RequestParam Integer id) {
        service.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
