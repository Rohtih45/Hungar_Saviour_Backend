package com.hunger.saviour.portal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "hunger-payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private UUID paymentId;
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;
    @Column(name = "txn_date_time")
    private LocalDateTime txnDateAndTime;
    @Column(name = "username")
    private String userName;
    @Column(name = "txn_id")
    private String transactionId;
}
