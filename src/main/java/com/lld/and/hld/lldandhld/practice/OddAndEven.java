package com.lld.and.hld.lldandhld.practice;
// data = 10

// 2 threads 

//     Thread1 print odd numbers
//     Thread2 print even numbers

/**
 * OddAndEven
 */
public class OddAndEven {

    private int data;
    private int currentData;

    public OddAndEven(int data) {
        this.data = data;
        this.currentData = 1;
    }

    synchronized void printOdd() throws InterruptedException {
        while (currentData <= this.data) {
            System.out.println(Thread.currentThread().getName() + " " + currentData);
            currentData++;
            notify();
            wait();
        }
        System.exit(0);
    }

    synchronized void printEven() throws InterruptedException {
        while (currentData <= this.data) {
            System.out.println(Thread.currentThread().getName() + " " + currentData);
            currentData++;
            notify();
            wait();
        }
        System.exit(0);
    }

    public static void main(String[] args) throws InterruptedException {
        OddAndEven oddAndEven = new OddAndEven(10);
        Thread threadOdd = new Thread(() -> {
            try {
                oddAndEven.printOdd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadEven = new Thread(() -> {
            try {
                oddAndEven.printEven();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadOdd.start();
        threadEven.start();
        
    }
}