package com.hunger.saviour.portal.repositories;

import com.hunger.saviour.portal.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
