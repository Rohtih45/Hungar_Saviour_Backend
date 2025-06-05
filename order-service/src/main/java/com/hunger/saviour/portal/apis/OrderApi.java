package com.hunger.saviour.portal.apis;

import com.hunger.saviour.portal.dtos.OrderDTO;
import com.hunger.saviour.portal.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderApi {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder (@RequestBody OrderDTO dto){
        this.orderService.createOrder(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable String username){
        return new ResponseEntity<>(this.orderService.getOrderByUserName(username),HttpStatus.OK);
    }
}
