package com.hungar.saviour.portal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantMenuDTO {

    private Integer restaurantMenuId;
    private String menuItem;
    private String description;
    private String menuImageUrl;
    private String menuType;
    private Double price;

}
