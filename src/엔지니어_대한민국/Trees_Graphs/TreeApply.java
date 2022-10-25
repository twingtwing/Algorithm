package 엔지니어_대한민국.Trees_Graphs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Tree 응용 : 이진 트리의 노드들을 각 레벨별로 Linked List에 담는 알고리즘을 구현하시오
 *            (예를 들어, 5개의 깊이를 가지는 트리라면, 5개의 LinkedList를 만들어야함)
 *           (1) level이 몊번인지 함수의 인자로 저장
 *           (2) BFS 변형
 *           => 모두 기본적으로 각각 O(n) 시간복잡도/공간복자도가 들어가지만,
 *            첫번째 방법은 재귀호출을 하는 동안 필요한 o(logn)의 공간 복잡도가 추가적으로 더 필요함
 */
class Tree2{
    
    class Node{
        int data;
        Node left = null;
        Node right = null;
        Node(int data){
            this.data = data;
        }
    }

    Node root;

    Tree2(int size){
        root = makeBST(0,size -1);
    }

    Node makeBST(int start, int end){
        if (start > end) return null;
        int mid = (start + end)/2;
        Node node= new Node(mid);
        node.left = makeBST(start,mid-1);
        node.right = makeBST(mid+1,end);
        return node;
    }

    // (1) level이 몊번인지 함수의 인자로 저장 + 재귀호출을 이용
    ArrayList<LinkedList<Node>> BSTtoList(){
        ArrayList<LinkedList<Node>> lists = new ArrayList<>();
        BSTtoList(root,lists,0);
        return lists;
    }

    void BSTtoList(Node node,ArrayList<LinkedList<Node>> lists,int level){
        if (node == null)return;

        LinkedList<Node> list = null;
        if (lists.size() == level){ //level 0부터 시작하기때문에 size = level이면 현재 lists크기가 level보다 1 작은 상태를 의미함
            list = new LinkedList<>();
            lists.add(list);
        }else {
            list = lists.get(level);
        }
        list.add(node);
        BSTtoList(node.left,lists,level+1);
        BSTtoList(node.right,lists,level+1);
    }

    //(2) BFS 변형
    ArrayList<LinkedList<Node>> BSTwithBFS(){
        ArrayList<LinkedList<Node>> result = new ArrayList<>();
        LinkedList<Node> current = new LinkedList<>();
        if (root != null){
            current.add(root); //초기값
        }
        while(current.size() > 0){
            result.add(current);
            LinkedList<Node> parents = current;
            current = new LinkedList<>();
            for(Node parent : parents){
                if (parent.left != null) current.add(parent.left);
                if (parent.right != null) current.add(parent.right);
            }
        }

        return result;
    }

    void printList(ArrayList<LinkedList<Node>> arr){
        for (LinkedList<Node> lists : arr){
            for (Node node : lists){
                System.out.print(node.data + " ");
            }
            System.out.println();
        }
    }

}

public class TreeApply {
    public static void main(String[] args){
        Tree2 tree = new Tree2(10);
        tree.printList(tree.BSTtoList());
        tree.printList(tree.BSTwithBFS());
    }
}
