package com.lld.and.hld.lldandhld.practice;

//class Node {
//    int data;
//    Node left, right;
//    Node(int data) {
//        this.data = data;
//        this.left = this.right = null;
//    }
//}
public class CorrectBST {

    public static void swap(Node a, Node b) {
        int temp = a.data;
        a.data = b.data;
        b.data = temp;
    }

    public static void correctBSTTree(Node root, Node leftChild, Node rightChild) {
        if(root == null || leftChild == null || rightChild == null) return;
        if(root.data < leftChild.data)  {
            swap(root, leftChild);
            return;
        } else if(root.data > rightChild.data) {
            swap(root, rightChild);
            return;
        } else if(leftChild.data > rightChild.data) {
            swap(leftChild, rightChild);
            return;
        }
        correctBSTTree(leftChild, leftChild.left, leftChild.right);
        correctBSTTree(rightChild, rightChild.left, rightChild.right);
    }

    public static void preOrder(Node root) {
        if(root == null) return;
        System.out.print(root.data + " "); 
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     *                          10
                      7                  12
	            5	    6           11    	18
     * @param args
     */

    public static void main(String[] args) {
        Node tree = new Node(10);

        tree.left = new Node(7);
        tree.right = new Node(12);

        tree.left.left = new Node(5);
        tree.left.right = new Node(6);

        tree.right.left = new Node(11);
        tree.right.right = new Node(18);
        correctBSTTree(tree, tree.left, tree.right);
        preOrder(tree);
    }
}
