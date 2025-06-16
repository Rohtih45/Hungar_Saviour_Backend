package com.hungar.saviour.portal.services;

import com.hungar.saviour.portal.dtos.RestaurantDTO;
import com.hungar.saviour.portal.entities.RestaurantEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RestaurantService {

    RestaurantDTO createRestaurant(RestaurantDTO dto);

    Page<RestaurantDTO> getRestaurants(int pageNumber, int pageSize);

    List<RestaurantDTO> getRestaurant();

    public RestaurantDTO getRestaurantById(Integer id) throws Exception;
}
