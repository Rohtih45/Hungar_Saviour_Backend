package com.hungar.saviour.portal.dtos;

import com.hungar.saviour.portal.entities.RestaurantMenuEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDTO {
    private Integer restaurantId;
    private String restaurantName;
    private String rating;
    private String imageUrl;
    private String location;
    private List<String> menuTypes;
    private List<RestaurantMenuDTO> menus;
}
