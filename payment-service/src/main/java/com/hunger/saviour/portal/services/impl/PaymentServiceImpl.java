package com.hunger.saviour.portal.services.impl;

import com.hunger.saviour.portal.broker.OrderBroker;
import com.hunger.saviour.portal.dtos.OrderDTO;
import com.hunger.saviour.portal.entities.PaymentEntity;
import com.hunger.saviour.portal.entities.PaymentStatus;
import com.hunger.saviour.portal.repositories.PaymentRepository;
import com.hunger.saviour.portal.services.PaymentService;
import jakarta.persistence.MapKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderBroker orderBroker;

    @Override
    public void processPayment(OrderDTO dto) {
        PaymentEntity entity= PaymentEntity.builder()
                .paymentStatus(PaymentStatus.PAYMENT_SUCCESS)
                .transactionId(dto.getTransactionId())
                .userName(dto.getUserName())
                .txnDateAndTime(LocalDateTime.now())  
                .build();
        this.paymentRepository.save(entity);
        // Make a call to order service to create order
        dto.setPaymentId(entity.getPaymentId());
        orderBroker.createOrder(dto);
        //
    }
}
