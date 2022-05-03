package com.lld.and.hld.lldandhld.designpattern;

import lombok.Getter;
import lombok.Setter;

public class DecoratorPattern {

    @Getter
    @Setter
    static class Pizza {
        private Double cost;

        public Pizza() {
            this.cost = 100D;
        }

        public Pizza calculatePrice(Pizza pizza) {
            return pizza;
        }

        public Double getTotalCost() {
            return this.getCost();
        }
    }

    static class Topping1 extends Pizza {
        private Double price;

        public Topping1() {
            this.price = 10D;
        }

        @Override
        public Pizza calculatePrice(Pizza pizza) {
            pizza.setCost(pizza.getCost() + this.price);
            return pizza;
        }
    }

    static class Topping2 extends Pizza {
        private Double price;

        public Topping2() {
            this.price = 20D;
        }

        @Override
        public Pizza calculatePrice(Pizza pizza) {
            pizza.setCost(pizza.getCost() + this.price);
            return pizza;
        }
    }

    static class Topping3 extends Pizza {
        private Double price;

        public Topping3() {
            this.price = 30D;
        }

        @Override
        public Pizza calculatePrice(Pizza pizza) {
            pizza.setCost(pizza.getCost() + this.price);
            return pizza;
        }
    }

    public static void main(String[] args) {
        Pizza pizza = new Pizza();
        Pizza t1 = new Topping1();
        Pizza t2 = new Topping1();
        Pizza t3 = new Topping1();
        System.out.println(t3.calculatePrice(t2.calculatePrice(t1.calculatePrice(pizza))).getTotalCost());
    }
}
