package com.hungar.saviour.portal.repository;

import com.hungar.saviour.portal.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Integer> {
}
