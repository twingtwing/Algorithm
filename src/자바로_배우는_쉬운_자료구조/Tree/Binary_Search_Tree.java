package 자바로_배우는_쉬운_자료구조.Tree;

class Node{
    char data;
    Node left;
    Node right;
}

class BinarySearchTree{
    private Node root;

    public void inserBST(char data){
        this.root = insertKey(this.root,data);
    }

    public Node insertKey(Node root, char data){
        Node newNode = new Node();
        newNode.data = data;
        if (root == null){
            return newNode;
        }else if(root.data > data){
            root.left = insertKey(root.left,data);
        }else if(root.data < data){
            root.right = insertKey(root.right,data);
        }
        return root;
    }

    public Node searchBST(char data){
        Node node = this.root;
        while(node != null){
            if (node.data == data){
                return node;
            }else if(node.data > data){
                node = node.left;
            }else if(node.data < data){
                node = node.right;
            }
        }
        return node;
    }

    public void inorder(){
        inorder(this.root);
        System.out.println();
    }

    public void inorder(Node node){
        if (node == null) return;
        inorder(node.left);
        System.out.printf("%c ",node.data);
        inorder(node.right);
    }
}

public class Binary_Search_Tree {
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        char [] chars = {'G','I','H','D','B','M','N','A','J','E','Q'};

        for (int i = 0; i < chars.length; i++){
            binarySearchTree.inserBST(chars[i]);
        }

        binarySearchTree.inorder();

        Node node = binarySearchTree.searchBST('A');
        if (node != null){
            System.out.println(node.data);
        }

    }
}
