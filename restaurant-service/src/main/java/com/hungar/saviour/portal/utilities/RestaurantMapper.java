package com.hungar.saviour.portal.utilities;

import com.hungar.saviour.portal.dtos.RestaurantDTO;
import com.hungar.saviour.portal.entities.RestaurantEntity;
import org.mapstruct.factory.Mappers;

public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    RestaurantDTO entityToDto(RestaurantEntity entity);
}
