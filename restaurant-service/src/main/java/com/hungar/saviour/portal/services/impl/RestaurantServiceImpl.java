package com.hungar.saviour.portal.services.impl;

import com.hungar.saviour.portal.dtos.RestaurantDTO;
import com.hungar.saviour.portal.entities.RestaurantEntity;
import com.hungar.saviour.portal.repository.RestaurantRepository;
import com.hungar.saviour.portal.services.RestaurantService;
import com.hungar.saviour.portal.utilities.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantDTO createRestaurant(RestaurantDTO dto){
        RestaurantEntity entity = new RestaurantEntity();
        BeanUtils.copyProperties(dto, entity);
        restaurantRepository.save(entity);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public Page<RestaurantDTO> getRestaurants(int pageNumber, int pageSize) {
        Page<RestaurantEntity> entities = this.restaurantRepository.findAll(PageRequest.of(pageNumber,pageSize));
        return entities.map(RestaurantMapper.INSTANCE::entityToDTO);
    }

    @Override
    public List<RestaurantDTO> getRestaurant() {
        List<RestaurantEntity> entities = restaurantRepository.findAll();

        return entities.stream().map(RestaurantMapper.INSTANCE::entityToDTO).toList();
    }

    public RestaurantDTO getRestaurantById(Integer id) throws Exception{
        return RestaurantMapper.INSTANCE.entityToDTOWithMenus(restaurantRepository.findById(id)
                .orElseThrow(()-> new Exception("Restaurant not found with id: "+id)));
    }

}
