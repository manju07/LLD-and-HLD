
package com.lld.and.hld.lldandhld.practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * RightSideViewOfBinaryTree
 * //           1
 * //         2   3
* //        4  5   8
 * //     7   9
 * //   12
 */


public class RightSideViewOfBinaryTree {

    public static void rightSideViewOfBT(Node root) {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int childNodesCount = 1;
        while (!queue.isEmpty()) {
            int tempChildNodesCount = 0;
            Node tempNode = null;
            while (childNodesCount != 0) {
                tempNode = queue.remove();
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                    tempChildNodesCount++;
                }
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                    tempChildNodesCount++;
                }
                childNodesCount--;
            }
            if (tempNode != null)
                System.out.print(tempNode.data + " ");

            childNodesCount = tempChildNodesCount;
        }
    }

    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);

        tree.left.left = new Node(4);
        tree.left.right = new Node(5);

        tree.right.right = new Node(8);

        tree.left.left.left = new Node(7);
        tree.left.left.right = new Node(9);

        tree.left.left.left.left = new Node(12);

        rightSideViewOfBT(tree);
    }

}
