package practice.tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 응용 1. 이진 검색 트리의 level별로 Linked List에 담는 알고리즘 구현
 * 응용 2. 주어진 이진트리의 balance가 맞는 지 확인하는 알고리즘
 *        (이때의 balance는 서브트리들간의 level이 1이상 차이가 나는 경우)
 */

class BinarySearchTree{
    
    Node root;
    
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){this.data = data;}
    }

    BinarySearchTree(int len){
        this.root = makeBST(1, len);
        this.root.right.right.right.right = new Node(11);
    }
    
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

    void printList(ArrayList<LinkedList<Node>> arr){
        for (LinkedList<Node> lists : arr){
            for (Node node : lists){
                System.out.print(node.data + " ");
            }
            System.out.println();
        }
    }

    // 응용 2.1 재귀함수
    // 시간 복잡도 : nlogn => 중간에 찾으면 끝나지만, level을 구하기 위해 한번은 돌기 때문이다.
    boolean isBalanced(Node node){
        if(node == null) return true; //밸런스 확인이기 때문
        int diff = getLevel(node.left) - getLevel(node.right);
        if(Math.abs(diff) >  1) return false;
        return isBalanced(node.left) && isBalanced(node.right); // 부모노드가 밸런스가 맞으면, 자식노드의 밸런스를 학인해야함
    }

    int getLevel(Node node){
        if(node == null) return -1;
        return Math.max(getLevel(node.left), getLevel(node.right)) + 1;
    }

    // 응용 2.2 재귀함수 2
    // 시간복잡도 : n => false대신 Integer.MIN_VALUE을 사용하여 재귀함수 한번만 돌도록 구현
    boolean isBalanced2(Node node){return checkLevel(node) != Integer.MIN_VALUE;}

    int checkLevel(Node node){
        if (node == null) return -1;

        int left = checkLevel(node.left);
        int rigth = checkLevel(node.right);
        if (left == Integer.MIN_VALUE || rigth == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        if (Math.abs(rigth - left) > 1) return Integer.MIN_VALUE;
        return Math.max(rigth,left) + 1;
    }

    // 응용 3. level을 저장하는 저장공간을 부여
    class Level{
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
    }
    
    boolean isBalanced3(Node node){
        Level obj = new Level();
        countLevel(node,obj,0);
        return Math.abs(obj.min - obj.max) <= 1;
    }
    
    // 재귀함수의 경우, 나오면서 계산하지만, 이번에는 들어가면서 값을 계산한다. 
    // 들어가면서 값을 계산해야지 중간에 결과가 나오면 멈출 수 있다.??? 아닌가?
    void countLevel(Node node, Level obj, int level){
        if (node == null){
            if (level < obj.max) obj.min = level;
            else if(level > obj.max) obj.max = level;
            return; // 마지막에 도달 하였기 때문에 멈춤
        }
        countLevel(node.left,obj,level+1);
        countLevel(node.right,obj,level+1);
    }

}

public class Binary_Tree_Apply {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree(10);
        tree.printList(tree.levelToList());
        tree.printList(tree.BFSToList());
        
    }
}
