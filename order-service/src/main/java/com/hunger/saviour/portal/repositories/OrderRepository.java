package com.hunger.saviour.portal.repositories;

import com.hunger.saviour.portal.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    Optional<List<OrderEntity>> findByUserName(String username);
}
