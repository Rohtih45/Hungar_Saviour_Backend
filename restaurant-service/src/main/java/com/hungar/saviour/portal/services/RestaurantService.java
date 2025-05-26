package com.hungar.saviour.portal.services;

import com.hungar.saviour.portal.entities.RestaurantEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RestaurantService {
    Page<RestaurantEntity> getRestaurants(int pageNumber, int pageSize);

    List<RestaurantEntity> getRestaurant();
}
