package com.hungar.saviour.portal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "restaurant")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "restaurant_name")
    private String restaurantName;
    @Column(name = "rating")
    private String rating;
    @Column(name = "restaurant_image_url")
    private String imageUrl;
    @Column(name = "location")
    private String location;

    @ElementCollection
    @CollectionTable(name = "restaurant_entity_menu_types", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "menu_types")
    private List<String> menuTypes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private List<RestaurantMenuEntity> menus;

}
