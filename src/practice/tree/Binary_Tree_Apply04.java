package practice.tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 응용 1 : 주어진 이진검색트리를 만들 수 있는 모든 배열을 찾는 알고리즘 구현(단, 트리에 중복값업음)
 *        (배열은 가장 왼쪽이 root이다.)
 *         => postorder 방식으로 양쪽 경우의수를 갖고와서 번갈아 list를 구하여 총 경우의 수를 구한다.
 *         !! keypoint !!
 *         이진검색트리이기 때문에 부모노드와 자식노드의 위아래가 바뀌지 않는 이상, 이전검색트리를 만들 수 있다.
 *
 * 응용 2 : 작은트리가 큰트리의 서브트리로 속하는지 알아보는 알고리즘 (단, 트리에 중복값업음) => preorder
 *          !! keypoint !!
 *          중복값이 없는 조건이므로 서로 일치하는 root위치만 찾아서 동시에 순회하면 된다.
 *
 * 응용 3 : 연결된 노드의 합산이 특정숫자가 되는 경로의 갯수를 구하는 알고리즘을 구현
 *         시작점, 끝점 상관없이 위에서 아래로만 합산하면 된다.
 *         !! keypoint !!
 *         위에서 아래이기 때문에 preorder 이다.
 */
class BinarySearch{

    Node root;

    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){this.data = data;}
    }

    BinarySearch(){}
    BinarySearch(int size){this.root = makeBST(0,size-1);}

    Node makeBST(int s, int e){
        if (s > e) return null;
        int m = (s+e)/2;
        Node node = new Node(m);
        node.left = makeBST(s,m-1);
        node.right = makeBST(m + 1,e);
        return node;
    }

    void inorder(){
        inorder(this.root);
        System.out.println();
    }

    void inorder(Node node){
        if (node == null)return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    // 응용 1
    // 이진검색트리이기 때문에 왼쪽 오른쪽 위치가 바껴도 이진검색트리로 구현이 가눙하다.
    // 부모 노드와 자식노드의 위치만 배열안에서 바뀌지 않으면 된다.

    // 이진 검색 트리를 만들 수 있는 배열을 가지고 오는 알고리즘
    ArrayList<LinkedList<Integer>> allSequences(Node node){
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();
        if (node == null){
            result.add(new LinkedList<>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.data);

        // 왼쪽 or 오른쪽을 먼저 가느냐에 따라 배열이 달라짐
        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

        // 왼쪽,오른쪽 경우의 수이기 때문에 left 경우의수 x right 경우의수 이므로 이중 for구문이다.
        for (LinkedList<Integer> left : leftSeq){
            for (LinkedList<Integer> right : rightSeq){
                // 경우의 수를 담음
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveList(left,right,weaved,prefix);
                result.addAll(weaved);
            }
        }

        return result;
    }

    // 경우의 수를 만들어 주는 알고리즘
    void weaveList(LinkedList<Integer> first, LinkedList<Integer> second,
                            ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix){
        if (first.isEmpty() || second.isEmpty()){
            LinkedList<Integer> result = new LinkedList<>();
            result.addAll(prefix);
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        // first
        prefix.addLast(first.removeFirst());
        weaveList(first,second,results,prefix);

        first.addFirst(prefix.removeLast()); //원복

        // second
        prefix.addLast(second.removeFirst());
        weaveList(first,second,results,prefix);

        second.addFirst(prefix.removeLast()); //원복

    }

    // 응용 2. preorder

    boolean containsTree(Node first, Node second){
        if (first == null || second == null) return false;
        return subTree(first,second);
    }

    // 중복값이 없는 조건이기 때문에 재귀함수를 돌면서 root를 찾는다.
    boolean subTree(Node root, Node sub) {
        if (root == null) return false;
        if (root.data == sub.data && matchTree(root,sub)) return true;
        return subTree(root.left,sub) || subTree(root.right,sub);
    }

    // 동시에 순회하면서, 값이 같은지 확인
    boolean matchTree(Node first, Node second){
        if (first == null && second == null) return true;
        else if(first == null || second == null) return false;
        else if(first.data != second.data) return false;
        return matchTree(first.left,second.left) && matchTree(first.right,second.right);
    }


    // 응용 3. preorder

    // 3.1 모든 노드마다 방법을 찾음 => 중복 순회

    // 3.2 저장곤간을 이용 => 중복 순회 방지

    // 3.3 노드간의 배열간 거리 설정해서 값 추가


}

public class Binary_Tree_Apply04 {
    public static void main(String[] args) {
        BinarySearch tree = new BinarySearch(5);

        tree.inorder();
        System.out.println();

        ArrayList<LinkedList<Integer>> result = tree.allSequences(tree.root);
        for (LinkedList<Integer> list : result){
            for (int data : list){
                System.out.print(data + " ");
            }
            System.out.println();
        }

        System.out.println();

        BinarySearch tree1 = new BinarySearch();
        BinarySearch tree2 = new BinarySearch();
        tree1.root = tree1.makeBST(0,9);
        tree2.root = tree2.makeBST(5,9);

        System.out.println(tree1.containsTree(tree1.root,tree2.root));

        BinarySearch tree3 = new BinarySearch();
        tree3.root = tree3.makeBST(7,9);

        System.out.println(tree1.containsTree(tree1.root,tree3.root));

    }

}
