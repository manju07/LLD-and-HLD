package com.lld.and.hld.lldandhld.practice;

import java.util.Stack;

/**
 * queue using stack
 * 2 stack
 * 
 * Stack1 - 4, 5, 6
 * Stack2 - 3, 2
 * 2, 3, 4, 5, 6
 */
public class QueueUsingStack {
    static Stack<Integer> stack1 = new Stack<>();
    static Stack<Integer> stack2 = new Stack<>();

    public static void insertIntoQueue(int data) {
        stack1.push(data);
    }

    public static Integer removeFromQueue() {
        if (stack1.isEmpty() && stack2.isEmpty())
            return -1;
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public static void main(String[] args) {

    }
}
