package com.orderingsystem.orderingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderingsystem.orderingservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
