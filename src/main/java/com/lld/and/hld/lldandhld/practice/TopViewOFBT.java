package com.lld.and.hld.lldandhld.practice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Print the top view of the Binery Tree.
 * 
 * Node {
 * Int value;
 * Node -> left, right
 * }
 * 
 * 8
 * / \
 * 3 14
 * / / \
 * 1 2 11
 * / \ /
 * 4 7 13
 * \ \
 * 5 12
 * \
 * 15
 * 
 * HashMap
 * 0 - 8
 * 1 - 3
 * 2 - 1, 4
 * 3 - 1
 * 4 - 6
 * 5 - 7
 * 
 * 1 3 8 14 11
 * 
 * 8
 * / \
 * 3 14
 * / / \
 * 1 2 11
 * / \ /
 * 4 7 13
 * \
 * 5
 * 
 * 
 * 
 */

//class Node {
//    int data;
//    Node left, right;
//
//    Node(int data) {
//        this.data = data;
//        this.left = this.right = null;
//    }
//}

class CustomNode {
    Node node;
    int verticalLine;

    CustomNode(Node node, int verticalLine) {
        this.node = node;
        this.verticalLine = verticalLine;
    }
}

public class TopViewOFBT {

    public static void printTopViewOfBT(Node tree) {
        if (tree == null)
            return;
        Queue<CustomNode> queue = new LinkedList<>();
        queue.add(new CustomNode(tree, 0));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, tree.data);
        while (!queue.isEmpty()) {
            CustomNode customData = queue.remove();
            Node temp = customData.node;
            if (!map.containsKey(customData.verticalLine)) {
                map.put(customData.verticalLine, temp.data);
            }
            if (temp.left != null) {
                CustomNode leftCustomNode = new CustomNode(temp.left, customData.verticalLine + 1);
                queue.add(leftCustomNode);
            }
            if (temp.right != null) {
                CustomNode rightCustomNode = new CustomNode(temp.right, customData.verticalLine - 1);
                queue.add(rightCustomNode);
            }
        }
    }

    public static void main(String[] args) {
        
    }

}
