package com.hungar.saviour.portal.utilities;

import com.hungar.saviour.portal.dtos.RestaurantDTO;
import com.hungar.saviour.portal.entities.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {


    RestaurantDTO entityToDto(RestaurantEntity entity);
}
//RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);