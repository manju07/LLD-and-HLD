package com.lld.and.hld.lldandhld.designpattern.structural;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Decorator Pattern will add extra responsibilities to the object
 */

/**
 * Meal
 */
@Data
@NoArgsConstructor
class Meal {

    private List<String> items;

    public Meal(List<String> items) {
        this.items = items;
    }

    public Meal addItem(Meal meal) {
        return meal;
    }
}

class Icecream extends Meal {

    @Override
    public Meal addItem(Meal meal) {
        meal.getItems().add("Icecream");
        return meal;
    }
}

class Beverage extends Meal {

    @Override
    public Meal addItem(Meal meal) {
        meal.getItems().add("Lemon Juice");
        return meal;
    }
}

class Sweeet extends Meal {

    @Override
    public Meal addItem(Meal meal) {
        meal.getItems().add("Dharwad Pedha");
        return meal;
    }
}

public class DecoratorPatternWithMeal {
    public static void main(String[] args) {
        Meal meal = new Meal(new ArrayList<>(
                Stream.of(new String[] { "Chapati", "Rice", "Sambhar", "Curd", "Dal" }).collect(Collectors.toList())));
        Icecream icecream = new Icecream();
        Beverage beverage = new Beverage();
        Sweeet sweeet = new Sweeet();
        System.out.println(sweeet.addItem(beverage.addItem(icecream.addItem(meal))).getItems());
    }
}
