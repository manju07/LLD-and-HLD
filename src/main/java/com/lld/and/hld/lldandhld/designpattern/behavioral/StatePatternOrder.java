//package com.lld.and.hld.lldandhld.designpattern.behavioral;
//
///**
//State Pattern
//
//Item
//    Ordered -> shipped to near inventory  -> out for delievery -> delievered -> received
//
//We want to know the state of ordered item.
// */
//
///**
// * AllStates
// */
//interface Package {
//    void nextState(Item item);
//
//    void prevState(Item item);
//
//    void printState(Item item);
//}
//
///**
// * Item
// */
//class Item {
//    Package state = new Ordered();
//
//    public void nextState() {
//        state.nextState(this);
//    }
//
//    public void prevState() {
//        state.prevState(this);
//    }
//
//    public void printState() {
//        state.printState(this);
//    }
//
//    public Package getState() {
//        return state;
//    }
//
//    public void setState(Package state) {
//        this.state = state;
//    }
//
//}
//
//class Ordered implements Package {
//
//    @Override
//    public void nextState(Item item) {
//        item.setState(new Delievered());
//    }
//
//    @Override
//    public void prevState(Item item) {
//        System.out.println("No previous state as its just ordered");
//    }
//
//    @Override
//    public void printState(Item item) {
//        System.out.println("Item is ordered");
//    }
//
//}
//
//class Delievered implements Package {
//
//    @Override
//    public void nextState(Item item) {
//        item.setState(new Received());
//    }
//
//    @Override
//    public void prevState(Item item) {
//        item.setState(new Ordered());
//    }
//
//    @Override
//    public void printState(Item item) {
//        System.out.println("Order is delievered");
//    }
//
//}
//
//class Received implements Package {
//
//    @Override
//    public void nextState(Item item) {
//        System.out.println("Order already received by client");
//    }
//
//    @Override
//    public void prevState(Item item) {
//        item.setState(new Delievered());
//    }
//
//    @Override
//    public void printState(Item item) {
//        System.out.println("Order is received");
//    }
//
//}
//
///**
// * StatePatternOrder
// */
//
//@ExtendWith(MockitoExtension.class)
//public class StatePatternOrder {
//    public static void main(String[] args) {
//        Item item = new Item();
//        // System.out.println("\n\n");
//        item.printState();
//
//        item.nextState();
//        item.printState();
//
//        item.nextState();
//        item.printState();
//
//        item.nextState();
//        item.printState();
//
//        item.prevState();
//        item.printState();
//
//
//        item.prevState();
//        item.printState();
//
//        item.prevState();
//        item.printState();
//    }
//}