package practice.tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 응용 1. 이진 검색 트리의 level별로 Linked List에 담는 알고리즘 구현
 */

class BinarySearchTree{
    
    Node root;
    
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){this.data = data;}
    }

    BinarySearchTree(int len){this.root = makeBST(1, len);}
    
    boolean isEmpty(){return this.root == null;}

    Node makeBST(int start, int end) {
        if (start > end) return null;
        int middle = (start+end)/2;
        Node node = new Node(middle);
        node.left = makeBST(start, middle - 1);
        node.right = makeBST(middle + 1, end);
        return node;
    }
    
    // 응용 1.1 level 저장공간 부여
    ArrayList<LinkedList<Node>> levelToList(){
        ArrayList<LinkedList<Node>> lists = new ArrayList<>();
        levelToList(lists,this.root,0);
        return lists;
    }

    void levelToList(ArrayList<LinkedList<Node>> lists, Node node, int level){
        if (node == null) return;

        LinkedList<Node> list = null;
        if (lists.size() == level ) {
            list = new LinkedList<>();
            lists.add(list);
        }else{
            list = lists.get(level);
        }

        list.add(node);
        levelToList(lists,node.left,level + 1);
        levelToList(lists,node.right,level + 1);
    }
    
    // 응용 1.2 BFS(너비 우선 탐색) 변형
    ArrayList<LinkedList<Node>> BFSToList() {
        ArrayList<LinkedList<Node>> lists = new ArrayList<>();

        LinkedList<Node> cur = new LinkedList<>();

        if (!isEmpty()) cur.add(this.root);

        // 너비우선탐색 변형
        while (cur.size() > 0) {
            lists.add(cur);

            LinkedList<Node> prev = cur;
            cur = new LinkedList<>();

            for (Node p : prev) {
                if (p.left != null) cur.add(p.left);
                if (p.right != null) cur.add(p.right);
            }

        }

        return lists;
    }
    

}

public class Binary_Tree_Apply {
    public static void main(String[] args) {
        
    }
}
