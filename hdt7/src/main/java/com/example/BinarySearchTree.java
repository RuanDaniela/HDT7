package com.example;
// Implementación del Binary Search Tree (BST)
class BinarySearchTree<E extends Comparable<E>> {
    private class Node {
        E data;
        Node left, right;
        Node(E data) { this.data = data; }
    }

    private Node root;

    public void insert(E data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, E data) {
        if (root == null) return new Node(data);
        if (data.compareTo(root.data) < 0) root.left = insertRec(root.left, data);
        else root.right = insertRec(root.right, data);
        return root;
    }

    public E search(E key) {
        return searchRec(root, key);
    }

    private E searchRec(Node root, E key) {
        if (root == null || root.data.equals(key)) return root == null ? null : root.data;
        if (key.compareTo(root.data) < 0) return searchRec(root.left, key);
        return searchRec(root.right, key);
    }

    public boolean isEmpty() {
        return root == null;
    }
}
