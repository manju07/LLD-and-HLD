
//     1
//   /  \
//  2    3 = $1000
// / \  / \
// 4 5 6   7

package com.lld.and.hld.lldandhld.practice;

//class Node {
//    int data;
//    Node left, right;
//
//    Node(int data) {
//        this.data = data;
//        left = right = null;
//    }
//}



/**
 * LeftSubtree
 */
public class LeftSubtree {

    public static void printLeftSubtree(Node root, Boolean isLeftNode) {
        if (root == null)
            return;
        if (isLeftNode)
            System.out.println(root.data + " ");
        if (root.left != null)
            printLeftSubtree(root.left, true);
        if (root.right != null)
            printLeftSubtree(root.right, false);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        printLeftSubtree(root.left, true);
        printLeftSubtree(root.right, false);
    }
}