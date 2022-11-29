package practice.tree;

import java.util.NoSuchElementException;
import java.util.Random;

// 1. BinaryTree 구현
class Node<T>{
    T data;
    Node left;
    Node right;

    Node(){}
    Node(T data){this.data = data;}
}

class BTLinkedList<T>{

    private Node root;

    public Node makeTree(Node left, Node right, T data){
        Node result = new Node(data);
        result.left = left;
        result.right = right;
        return result;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preOrder(){
        preOrder(this.root);
    }

    private void preOrder(Node node){
        if (node == null) return;
        System.out.print(node.data + " -> ");
        preOrder(node.left);
        preOrder(node.right);
    }

}

// 2. BinarySearchTree 구현

// 2.1 Linked List로 구현
class BSTLinkedList{

    private Node root;

    class Node{
        int data;
        Node left;
        Node right;
        Node(){}
        Node(int data){this.data = data;}
    }

    boolean isEmpty(){return this.root == null;}

    // 1. 검색 연산
    public Node search(int data){
        if (isEmpty()) throw new NoSuchElementException();
        return search(this.root, data);
    }

    public Node search(Node node, int data){
        if (node == null || node.data == data) return node;
        if (node.data > data) return search(node.left,data);
        return search(node.right,data);
    }

    // 2. 삽입 연산
    public void insert(int data){this.root = insert(this.root, data);}

    private Node insert(Node node, int data) {
        if (node == null) return new Node(data);
        if (node.data > data) node.left = insert(node.left,data);
        if (node.data < data) node.right = insert(node.right,data);
        return node;
    }

    // 3. 삭제 연산
    public void delete (int data){
        if (isEmpty()) throw new NoSuchElementException();
        this.root = delete(this.root,data);
    }

    private Node delete(Node node, int data) {
        if (node == null) return node;
        if (node.data > data) node.left = delete(node.left,data);
        else if (node.data < data) node.right = delete(node.right,data);
        else{
            //경우 1. 자식이 없는 경우
            if (node.left == null && node.right == null) return null;

            // 경우 2. 자식이 한개 있는 경우
            else if (node.left == null || node.right == null) return node.left == null ? node.right : node.left;

            // 경우 3. 자식이 2개 있는 경우
            node.data = findMin(node.right); // link field 없이 data field만 가지고 와야함
            node.right = delete(node.right,node.data); // 값을 교체한 Node 삭제

        }
        return node;
    }

    private int findMin(Node node){
        if (node.left == null) return node.data;
        return findMin(node.left);
    }

    // 순회
    public void inOrder(){
        if (isEmpty()) throw new NoSuchElementException();
        inOrder(this.root);
        System.out.println();
    }

    private void inOrder(Node node){
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }
}

// 2.2 Linear List로 구현
class BSTLinearList{

    private Node root;

    class Node{
        int data;
        Node left;
        Node right;
        Node(){}
        Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    boolean isEmpty(){return this.root == null;}

    // 1. 배열로 insert 구현
    void insertArray(int [] ary){this.root = insertArray(ary, 0, ary.length-1);}

    Node insertArray(int [] ary, int start, int end){
        if (start > end) return null;

        int middle = (start + end)/2;

        return new Node(ary[middle],
                insertArray(ary,start,middle-1),
                insertArray(ary,middle+1, end));

    }

    // 순회
    public void inOrder(){
        if (isEmpty()) throw new NoSuchElementException();
        inOrder(this.root);
        System.out.println();
    }

    private void inOrder(Node node){
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }
}

public class Binary_Tree_Basic {
    public static void main(String[] args) {
//        BTLinkedList<Integer> bTree = new BTLinkedList<>();
//
//        Node node05 = bTree.makeTree(null,null,5);
//        Node node04 = bTree.makeTree(null,null,4);
//        Node node03 = bTree.makeTree(null,null,3);
//        Node node02 = bTree.makeTree(null,node05,2);
//        Node node01 = bTree.makeTree(node03,node04,1);
//        Node result = bTree.makeTree(node01,node02,6);
//
//        bTree.setRoot(result);
//
//        bTree.preOrder();

//        BSTLinkedList bsTree = new BSTLinkedList();
//
//        for (int i = 0; i < 10; i++){
//            bsTree.insert(i);
//        }
//
//        System.out.println(bsTree.search(5).data);
//
//        bsTree.inOrder();
//
//        bsTree.delete(5);
//
//        bsTree.inOrder();

        BSTLinearList bsTree2 = new BSTLinearList();

        bsTree2.insertArray(new int[] {1,2,3,4,5});

        bsTree2.inOrder();

    }
}
