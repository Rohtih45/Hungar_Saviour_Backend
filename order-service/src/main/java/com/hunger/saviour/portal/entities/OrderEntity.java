package com.hunger.saviour.portal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "hungar-order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private UUID orderId;
    @Column(name = "payment_id")
    private UUID paymentId;
    @Column(name = "txn_id")
    private String transactionId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "amount_paid")
    private Double amountPaid;
    @Column(name = "order_created_date_time")
    private LocalDateTime orderCreateTime;
    @Column(name = "order_status")
    private String orderStatus;
}
