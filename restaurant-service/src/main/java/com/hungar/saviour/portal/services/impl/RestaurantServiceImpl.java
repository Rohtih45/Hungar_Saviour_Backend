package com.hungar.saviour.portal.services.impl;

import com.hungar.saviour.portal.entities.RestaurantEntity;
import com.hungar.saviour.portal.repository.RestaurantRepository;
import com.hungar.saviour.portal.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Override
    public Page<RestaurantEntity> getRestaurants(int pageNumber, int pageSize) {
        return restaurantRepository.findAll(PageRequest.of(pageNumber,pageSize));
    }

    @Override
    public List<RestaurantEntity> getRestaurant() {
        return restaurantRepository.findAll();
    }
}
