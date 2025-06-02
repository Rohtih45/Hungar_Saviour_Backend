package com.hungar.saviour.portal.apis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hungar.saviour.portal.dtos.RestaurantDTO;
import com.hungar.saviour.portal.entities.RestaurantEntity;
import com.hungar.saviour.portal.repository.RestaurantRepository;
import com.hungar.saviour.portal.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantAPI {

    private final ResourceLoader resourceLoader;
    private final RestaurantRepository restaurantRepository;
    private final ObjectMapper objectMapper;
    private final RestaurantService service;

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<RestaurantEntity> getRestaurants(@PathVariable Integer pageNumber,@PathVariable Integer pageSize){
        return this.service.getRestaurants(pageNumber, pageSize);
    }

    @GetMapping("/loadRest")
    public List<RestaurantDTO> getRestaurants(){
        return this.service.getRestaurant();
    }



    @GetMapping("/load")
    public String loadRestaurantsInDatabase() throws Exception{
        Resource resource = resourceLoader.getResource("classpath:"+"restaurants.json");
        try(InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)){
            String data = FileCopyUtils.copyToString(reader);
            List<RestaurantEntity> restaurantEntities = objectMapper.readValue(data, new TypeReference<List<RestaurantEntity>>(){});
            this.restaurantRepository.saveAll(restaurantEntities);
        }

        return "Records Inserted";
    }
}
