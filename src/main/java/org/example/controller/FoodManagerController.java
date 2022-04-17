package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.FoodManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@CrossOrigin
@AllArgsConstructor
@Controller
public class FoodManagerController {

    private final FoodManagerService foodManagerService;

    @GetMapping("/foods.html")
    public String all(Model model) {
        log.info("FOODS READ ALL");
        model.addAttribute("foods", foodManagerService.searchAll());
        return "foods/list";
    }

    @GetMapping(value = "/foods.html", params = "foodId")
    public String get(@RequestParam("foodId") Long foodId, Model model) {
        log.info("FOODS READ ONE");
        foodManagerService.searchByFoodId(foodId).ifPresent(food -> model.addAttribute("food", food));
        return "foods/details";
    }

    @GetMapping("/foods/500")
    public void error() {
        throw new NullPointerException("Dummy NullPointerException");
    }

}
