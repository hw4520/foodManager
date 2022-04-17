package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoodEntity {

    private Long foodId;
    private String foodName;
    private List<String> chefGroup = new ArrayList<>();

    public FoodEntity(Long foodId, String foodName, String... chefGroup) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.chefGroup.addAll(Arrays.asList(chefGroup));
    }

    public List<String> getChefGroup() {
        return Collections.unmodifiableList(chefGroup);
    }

    @Override
    public String toString() {
        return String.format("Food [foodId=%s, foodName=%s, chefGroup=%s]", this.foodId, this.foodName, this.chefGroup);
    }
}
