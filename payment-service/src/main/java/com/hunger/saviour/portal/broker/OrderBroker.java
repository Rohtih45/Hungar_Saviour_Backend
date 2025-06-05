package com.hunger.saviour.portal.broker;

import com.hunger.saviour.portal.dtos.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service")
public interface OrderBroker {

    @PostMapping("/orders")
    ResponseEntity<?> createOrder ( OrderDTO dto);
}
