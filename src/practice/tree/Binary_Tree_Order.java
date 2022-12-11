package practice.tree;

/**
 * LinearList로 구현된 Tree를 순회를 통해 LinkedList로 구현
 * => 1개를 가지고 경우의 수가 많아지기 때문에 2개의 순회를 동시 사용하여 Tree를 구현
 * => 두개의 순회를 통해 서브트리의 root를 찾아 Tree를 구현한다.
 * => Preorder + Postorder은 구현이 가능하지만, full 이진트리가 아니고, 밸런스가 좋지 않으면 구현이 힘들다
 */
class BinaryTreeOrder{

    Node root;
    int index = 0; // 배열 index

    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){this.data = data;}
    }

    // 1. Preorder  +  Inorder
    public void buildTreeByPreIn(int [] in, int [] pre){
        this.index = 0; // 이때는 preorder의 index
        this.root = buildTreeByPreIn(in,pre,0,in.length-1);
    }

    private Node buildTreeByPreIn(int[] in, int[] pre, int inStart, int inEnd) {
        if (inStart > inEnd) return null;
        Node node = new Node(pre[index++]);
        int inMid = searchIndex(node.data, in, inStart, inEnd);
        node.left = buildTreeByPreIn(in, pre, inStart, inMid-1);
        node.right = buildTreeByPreIn(in, pre, inMid + 1, inEnd);
        return node;
    }

    // 2. Postorder + Inorder
    public void buildTreeByPostIn(int [] in,  int [] post){
        this.index = post.length -1;
        this.root = buildTreeByPostIn(in, post, 0, in.length - 1);
    }

    private Node buildTreeByPostIn(int[] in, int[] post, int inStart, int inEnd) {
        if (inStart > inEnd) return null;
        Node node = new Node(post[index--]);
        int inMid = searchIndex(node.data, in, inStart, inEnd);

        // Postorder이기때문에 right 먼저 해야함
        node.right = buildTreeByPostIn(in, post, inMid + 1, inEnd);
        node.left = buildTreeByPostIn(in, post, inStart, inMid - 1);
        return node;
    }

    private int searchIndex(int data, int[] in, int inStart, int inEnd) {
        for (int i = inStart; i <= inEnd; i++) if (in[i] == data) return i;
        return Integer.MIN_VALUE;
    }

    // 출력
    public void printInOrder(){printInOrder(this.root);}

    private void printInOrder(Node node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }

}

public class Binary_Tree_Order {
    public static void main(String[] args) {
        BinaryTreeOrder tree = new BinaryTreeOrder();
        int [] pre = {4,2,1,3,6,5,7};
        int [] in = {1,2,3,4,5,6,7};
        int [] post = {1,3,2,5,7,6,4};

        tree.buildTreeByPreIn(in,pre);
        tree.printInOrder();
        System.out.println();

        tree.buildTreeByPostIn(in,post);
        tree.printInOrder();
        System.out.println();

    }
}
