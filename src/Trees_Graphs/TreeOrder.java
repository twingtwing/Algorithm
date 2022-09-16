package Trees_Graphs;
/**
 * Tree 순회방법(Inorder,PreOrder,PostOrder)
 */
class Node{
    int data;
    Node left;
    Node right;
}

class Tree{
    public Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node makeNode(Node left, int data, Node right){
        Node node = new Node();
        node.data = data;
        node.left = left;
        node.right = right;
        return node;
    }

    public void inorder(Node node){
        if (node != null){
            inorder(node.left);
            System.out.print(node.data + "\t");
            inorder(node.right);
        }
    }

    public void preorder(Node node){
        if (node != null){
            System.out.print(node.data + "\t");
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void postorder(Node node){
        if (node != null){
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + "\t");
        }
    }
}

public class TreeOrder {
    public static void main(String [] args){
        Tree tree = new Tree();
        Node n4 = tree.makeNode(null,4,null);
        Node n5 = tree.makeNode(null,5,null);
        Node n3 = tree.makeNode(null,3,null);
        Node n2 = tree.makeNode(n4,2,n5);
        Node n1 = tree.makeNode(n2,1,n3);

        tree.inorder(n1);
        System.out.println();
        tree.preorder(n1);
        System.out.println();
        tree.postorder(n1);

    }
}
