package com.hunger.saviour.portal.services.impl;

import com.hunger.saviour.portal.dtos.OrderDTO;
import com.hunger.saviour.portal.entities.OrderEntity;
import com.hunger.saviour.portal.repositories.OrderRepository;
import com.hunger.saviour.portal.services.OrderService;
import com.hunger.saviour.portal.utills.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final RestaurantMapper mapper;

    @Override
    public void createOrder(OrderDTO dto) {
        OrderEntity entity = mapper.entityToDto(dto);
        orderRepository.save(entity);
    }

    @Override
    public List<OrderDTO> getOrderByUserName(String username) {
        Optional<List<OrderEntity>> dtos = orderRepository.findByUserName(username);
        return dtos.map(orders ->
                        orders.stream()
                        .map(mapper::dtoToEntity)
                        .toList())
                .orElse(Collections.emptyList());
    }
}
