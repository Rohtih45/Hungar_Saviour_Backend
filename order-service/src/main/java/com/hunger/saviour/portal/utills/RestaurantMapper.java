package com.hunger.saviour.portal.utills;

import com.hunger.saviour.portal.dtos.OrderDTO;
import com.hunger.saviour.portal.entities.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {


    OrderEntity entityToDto(OrderDTO entity);

    OrderDTO dtoToEntity(OrderEntity entity);
}
//RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);