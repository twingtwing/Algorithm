package Trees_Graphs;
/**
 * 응용 : 이진 검색트리에서 주어진 노드의 다음 노드를 찾는 함수 구현(단, 다음노드의 순서는 inorder에 입각함)
 */
class Trees4 {
    class Node {
        int data;
        Node left;
        Node right;
        Node parent;

        Node(int data) {
            this.data = data;
        }
    }

    Node root;

    Trees4(int size) {
        root = makeBST(0, size - 1,null);
    }

    Node makeBST(int start, int end,Node parent) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1,node);
        node.right = makeBST(mid + 1, end,node);
        node.parent = parent;
        return node;
    }

    //inorder 방법
    void findNext(Node node){
        if (node.right == null){
            System.out.println(findAbove(node.parent, node).data + " is " + node.data + "'s Next");
        }else{
            System.out.println(findBelow(node.right).data + " is " + node.data + "'s Next");
        }
    }

    Node findAbove(Node root, Node child){
        if (root == null) return null;
        if (root.left == child) return root;
        return findAbove(root.parent,root);
    }

    Node findBelow(Node root){
        if (root.left == null) return root;
        return findBelow(root.left);
    }

}

public class TreeApply4 {
    public static void main(String [] args){
        Trees4 tree = new Trees4(10);
        tree.findNext(tree.root.left.right.right);
        tree.findNext(tree.root.left);
        tree.findNext(tree.root);
        tree.findNext(tree.root.left.left);
    }
}
