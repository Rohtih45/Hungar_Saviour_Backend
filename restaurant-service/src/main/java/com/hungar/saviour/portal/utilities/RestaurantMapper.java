package com.hungar.saviour.portal.utilities;

import com.hungar.saviour.portal.dtos.RestaurantDTO;
import com.hungar.saviour.portal.entities.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    RestaurantDTO entityToDTO(RestaurantEntity restaurantEntity);

    RestaurantDTO entityToDTOWithMenus(RestaurantEntity restaurantEntity);

    RestaurantEntity DTOtoEntity(RestaurantDTO restaurantDTO);
}

