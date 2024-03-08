package com.lld.and.hld.lldandhld.designpattern.structural;

/**
 * Decorator Pattern will add extra responsibilities to the object
 */

public class DecoratorPatternWithMealNewSolution {

    /**
     * Item
     */
    interface Item {
        String getItem();
    }

    static class Meal implements Item {

        @Override
        public String getItem() {
            return "Meal";
        }
    }

    static class Icecream implements Item {
        Item item;

        public Icecream(Item item) {
            this.item = item;
        }

        @Override
        public String getItem() {
            return item.getItem() + " with Icecream";
        }

    }

    static class Beverage implements Item {
        Item item;

        public Beverage(Item item) {
            this.item = item;
        }

        @Override
        public String getItem() {
            return item.getItem() + " with Beverage";
        }

    }

    static class Sweeet implements Item {
        Item item;

        public Sweeet(Item item) {
            this.item = item;
        }

        @Override
        public String getItem() {
            return item.getItem() + " with Sweeet";
        }

    }

    public static void main(String[] args) {
        Meal meal = new Meal();
        Icecream icecream = new Icecream(meal);
        Beverage beverage = new Beverage(icecream);
        Sweeet sweeet = new Sweeet(beverage);
        System.out.println(sweeet.getItem());
    }
}
