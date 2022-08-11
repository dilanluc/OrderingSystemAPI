package com.orderingsystem.orderingservice.controller;

import com.orderingsystem.orderingservice.dto.OrderRequest;
import com.orderingsystem.orderingservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.orderingsystem.orderingservice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void placeOrder(@RequestBody OrderRequest orderRequest) {
		orderService.placeOrder(orderRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Order> findAll() {
		return orderService.findAll();
	}
	
	
}
