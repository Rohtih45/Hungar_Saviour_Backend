package com.hunger.saviour.portal.apis;

import com.hunger.saviour.portal.dtos.OrderDTO;
import com.hunger.saviour.portal.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentAPI {

    private final PaymentService paymentService;

    public ResponseEntity<?> processPayment(@RequestBody OrderDTO dto){
        log.info("Entered into Payment API with data: "+ dto);
        paymentService.processPayment(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
