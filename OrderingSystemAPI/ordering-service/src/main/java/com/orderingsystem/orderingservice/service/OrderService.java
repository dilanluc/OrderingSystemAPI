package com.orderingsystem.orderingservice.service;

import com.orderingsystem.orderingservice.dto.OrderItemsDto;
import com.orderingsystem.orderingservice.dto.OrderRequest;
import com.orderingsystem.orderingservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.orderingsystem.orderingservice.model.OrderItems;
import com.orderingsystem.orderingservice.repository.OrderRepository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final WebClient webClient;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());

		List<OrderItems> orderItems = orderRequest.getOrderItemsDtoList()
				.stream()
				.map(this::mapToDto)
				.collect(Collectors.toList());

		order.setOrderItemsList(orderItems);

		List<String> names = order.getOrderItemsList().stream().map(OrderItems::getName).collect(Collectors.toList());
		List<Integer> quantities = order.getOrderItemsList().stream().map(OrderItems::getQuantity).collect(Collectors.toList());
		if(quantities.stream().anyMatch(q -> q <= 0)){
			throw new IllegalArgumentException("Product quantity must be higher than 0.");
		}

		boolean allProductsInStock = webClient.get()
				.uri("http://localhost:8081/api/product", uriBuilder -> uriBuilder.queryParam("name", names).queryParam("quantity", quantities).build())
				.retrieve()
				.bodyToMono(Boolean.class)
				.block();

		if(allProductsInStock){
			webClient.put()
					.uri("http://localhost:8081/api/product/updateStock", uriBuilder -> uriBuilder.queryParam("name", names).queryParam("quantity", quantities).build())
					.retrieve()
					.bodyToMono(Boolean.class)
					.block();
			order.setOrderState("running");
			orderRepository.save(order);
		} else {
			throw new IllegalArgumentException("Not all products are in stock");
		}
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	private OrderItems mapToDto(OrderItemsDto orderItemsDto) {
		OrderItems orderItems = new OrderItems();
		orderItems.setQuantity(orderItemsDto.getQuantity());
		orderItems.setName(orderItemsDto.getName());
		return orderItems;
	}

}
