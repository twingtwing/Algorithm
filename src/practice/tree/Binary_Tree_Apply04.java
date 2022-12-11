package practice.tree;

import java.util.ArrayList;
import java.util.HashMap;
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

    // 응용 3. preorder => 위에서 아래롤 내려가야 하기 때문에

    // 3.1 모든 노드마다 방법을 찾음 => 중복 순회가 발생
    // 시간 복잡도 : O(nlogn) ~  O(n^2)
    int countPath(int targetSum){return countPath(this.root,targetSum);}

    // 시작점을 어디로 할지를 정하는 순회
    private int countPath(Node node, int targetSum) {
        if (node == null) return 0;
        int pathFromNow = countPathWithSum(node,targetSum,0);

        // 경로의 시작점이 무조건 root 필요가 없기 때문에 전체 노드를 모두 순회하여야한다.
        int pathFromLeft = countPath(node.left,targetSum);
        int pathFromRight = countPath(node.right,targetSum);

        return pathFromNow + pathFromLeft + pathFromRight;
    }

    // 시작점을 기준으로 경로를 구하는 순회
    private int countPathWithSum(Node node, int targetSum, int curSum) {
        if (node == null) return 0;
        int totalPath = 0;

        curSum += node.data;
        if (curSum == targetSum) totalPath++;

        totalPath += countPathWithSum(node.left,targetSum,curSum);
        totalPath += countPathWithSum(node.right,targetSum,curSum);

        return totalPath;
    }

    // 3.2 추가 저장공간을 이용 => 중복 순회 방지
    // 시간복잡도 : O(n*배열.length)
    int countPath2(int targetSum){return countPath2(this.root,targetSum, new ArrayList<>());}

    // 시작점을 구하는 순회 => n
    private int countPath2(Node node, int targetSum, ArrayList<Integer> sumAry) {
        if(node == null) return 0;
        int totalPath = 0;
        addLast(sumAry,node.data); // 현재 시작점을 추가
        totalPath += countPathWithAry(sumAry,targetSum); // 지금 시점까지의 경로를 구한다.
        totalPath += countPath2(node.left, targetSum, sumAry);
        totalPath += countPath2(node.right, targetSum, sumAry);
        removeLast(sumAry); // 현재 시작점을 삭제
        return totalPath;
    }

    // 시작점을 기준으로 경로를 구하기 위해 배열을 순회 => Array.size()
    // 이때 각 배열방은 시작점을 의미하고, 값은 시작점을 기준으로 현재 지점까지의 합을 의미한다.
    private int countPathWithAry(ArrayList<Integer> sumAry, int targetSum) {
        if (sumAry.isEmpty()) return 0;
        int totalPath = 0;
        for (Integer integer : sumAry) if (targetSum == integer) totalPath++;
        return totalPath;
    }

    // 모든 시작점에 값을더하고, 시작점을 추가한다.
    private void addLast(ArrayList<Integer> sumAry, int data) {
        for (int i = 0; i < sumAry.size(); i++) sumAry.set(i,sumAry.get(i) + data);
        sumAry.add(data);
    }

    // 모든 시작점에 값을 빼고, 시작점을 삭제한다.
    private void removeLast(ArrayList<Integer> sumAry) {
        int data = sumAry.remove(sumAry.size()-1);
        for (int i = 0; i < sumAry.size(); i++) sumAry.set(i,sumAry.get(i) - data);
    }

    // 3.3 노드간의 거리 HashMap에 저장 (배열 대신 HashMap)
    // 배열과는 달리 시작점에서의 현재 노드까지의 합산이 아닌 root에서부터 시작점까지의 합산을 저장한다.
    // (root-현재지점  = root-시작점 + 구하고자하는 값)을 이용해서 시작점 갯수를 구함으로써 경로를 구한다.
    int countPath3(int targetSum){
        HashMap<Integer,Integer> hashTable = new HashMap<>();
        hashTable.put(0,1);
        return countPath3(this.root,targetSum,0,hashTable);
    }

    private int countPath3(Node node, int targetSum, int curSum, HashMap<Integer,Integer> hashTable){
        if(node == null) return 0;
        curSum += node.data;

        // root 부터 현재 노드까지의 합산에서 원하는 값을 뺀값과 root부터의 합산이 같은 시작점을 찾으면 된다.
        int minus = curSum - targetSum;
        int totalPath = hashTable.getOrDefault(minus, 0);

        increHashTable(hashTable, curSum, 1);
        totalPath += countPath3(node.left, targetSum, curSum, hashTable);
        totalPath += countPath3(node.right, targetSum, curSum, hashTable);
        increHashTable(hashTable, curSum, -1);

        return totalPath;
    }

    // key  : root 부터 현재 지점까지의 합산
    // value : 값은 key을 가진 시작점 갯수
    void increHashTable(HashMap<Integer,Integer> hashTable, int key, int val){
        int newCount = hashTable.getOrDefault(key,0) + val;
        if (newCount == 0) hashTable.remove(key);
        else hashTable.put(key,newCount);
    }
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

        BinarySearch tree4 = new BinarySearch(10);
        System.out.println(tree4.countPath(3));
        System.out.println(tree4.countPath2(3));
        System.out.println(tree4.countPath3(3));

    }

}
