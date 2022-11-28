package practice.tree;

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

// 2.1 Linear List로 구현
class BSTLinkedList{

}

// 2.2 Linear List로 구현
class BSTLinearList{

}

public class Binary_Tree_Basic {
    public static void main(String[] args) {
        BTLinkedList<Integer> bTree = new BTLinkedList<>();

        Node node05 = bTree.makeTree(null,null,5);
        Node node04 = bTree.makeTree(null,null,4);
        Node node03 = bTree.makeTree(null,null,3);
        Node node02 = bTree.makeTree(null,node05,2);
        Node node01 = bTree.makeTree(node03,node04,1);
        Node result = bTree.makeTree(node01,node02,6);

        bTree.setRoot(result);

        bTree.preOrder();

    }
}
