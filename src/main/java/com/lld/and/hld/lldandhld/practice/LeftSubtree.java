
//     1
//   /  \
//  2    3 = $1000
// / \  / \
// 4 5 6   7

package com.lld.and.hld.lldandhld.practice;

class Node {
    int data;
    Node leftNode, rightNode;

    Node(int data) {
        this.data = data;
        leftNode = rightNode = null;
    }
}



/**
 * LeftSubtree
 */
public class LeftSubtree {

    public static void printLeftSubtree(Node root, Boolean isLeftNode) {
        if (root == null)
            return;
        if (isLeftNode)
            System.out.println(root.data + " ");
        if (root.leftNode != null)
            printLeftSubtree(root.leftNode, true);
        if (root.rightNode != null)
            printLeftSubtree(root.rightNode, false);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        printLeftSubtree(root.leftNode, true);
        printLeftSubtree(root.rightNode, false);
    }
}