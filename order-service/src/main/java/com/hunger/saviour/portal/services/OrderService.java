package com.hunger.saviour.portal.services;

import com.hunger.saviour.portal.dtos.OrderDTO;

import java.util.List;

public interface OrderService {

    void createOrder(OrderDTO dto);
    List<OrderDTO> getOrderByUserName(String username);
}
