package com.Application.FilRouge.Repository;

import com.Application.FilRouge.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {


    @Query(value = "select r from Restaurant r where r.name=:name")
    public List<Restaurant> findByName(String name);


}
