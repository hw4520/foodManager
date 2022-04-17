package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.FoodEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class FoodManagerService {

    private final Map<Long, FoodEntity> foods = new ConcurrentHashMap<>();

    public FoodEntity addFoodItem(FoodEntity food) {
        foods.put(food.getFoodId(), food);
        return food;
    }

    public Optional<FoodEntity> searchByFoodId(Long foodId) {
        return Optional.ofNullable(foods.get(foodId));
    }

    public Iterable<FoodEntity> searchAll() {
        return foods.values();
    }

}
