package com.bootstrappers.backend;

/* https://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/ */

public class BinarySearchTree<Integer> {

    public Node root;
    public class Node{
        int data;
        Node left;
        Node right;
        /**
         * Constructor.
         * @param data data to be initialized.
         */
        public Node(int data){
            this.data = data;
            left = null;
            right = null;
        }
    }
    /**
     * Constructor.
     */
    public BinarySearchTree(){
        this.root = null;
    }

    /**
     * Insert method for BST.
     * @param id item to be inserted.
     */
    public void insert(int id){
        Node newNode = new Node(id);
        if(root==null){
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while(true){
            parent = current;
            if(id<current.data){
                current = current.left;
                if(current==null){
                    parent.left = newNode;
                    return;
                }
            }else{
                current = current.right;
                if(current==null){
                    parent.right = newNode;
                    return;
                }
            }
        }
    }
}