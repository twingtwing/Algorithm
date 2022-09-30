package Trees_Graphs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 응용 : 주어진 이진트리의 노든에는 정수값이 저장되어 있음(음의 정수 포함)
 *       연결된 노드의 합산이 특정 숫자가 되는 경로의 갯수를 찾으시오
 *       경로의 시작과 끝점은 어디든 될 수 있지만, 언제나 위에서 아래로만 합산이 되어야한다.
 *       => 위에서 아래이기 때문에 Preorder 방식으로 해야한다.
 *       => 그러나 만약에 이러면 모든 노드에서의 경로를 모두 알아야 하므로 시간 복잡도가 O(nlogn)에서 O(m^2)이 걸림
 *       => 이렇게 되면, 중복 검색 또한 일어남 그렇기에 추가공간을 이용하여 중복 순회를 막는다. (추가공간 : 배열\)
 *          시간복잡도 O(nd) d = level을 가짐
 *       =>
 */
class Tree6{
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }

    Node root;

    Tree6(int size){
        root = makeBST(0,size-1);
    }

    Node makeBST(int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1);
        node.right = makeBST(mid + 1, end);
        return node;
    }

    //방법1. 모든 노드마다 방법을 찾음 (중복 순회가 발생)
    int countPathsWithSum(int targetSum){
        return countPathsWithSum(root,targetSum);
    }

    int countPathsWithSum(Node node,int targetSum){
        if (node == null) return 0;
        int pathsFromRoot = countPathsWithSumFromRoot(node,targetSum,0); //현재 노드를 기점으로 경로의 갯수를 산출
        int pathsFromLeft = countPathsWithSum(node.left,targetSum); //왼쪽 서브트리 경로 갯수
        int pathsFromRight = countPathsWithSum(node.right,targetSum); // 오른쪽 서브트리 경로 갯수
        return pathsFromRoot + pathsFromLeft + pathsFromRight;
    }

    int countPathsWithSumFromRoot(Node node,int targetSum, int currSum){
        if (node == null) return 0;
        currSum += node.data;
        int totalPaths = 0;
        if (currSum == targetSum){
            totalPaths ++;
        }
        totalPaths += countPathsWithSumFromRoot(node.left,targetSum,currSum);
        totalPaths += countPathsWithSumFromRoot(node.right,targetSum,currSum);
        return totalPaths;
    }

    //방법2. 모두 순회하지만, 계산값을 저장하여 중복순회를 막음)
    int countPathsWithSum2(int targetSum){
        ArrayList<Integer> array = new ArrayList<>();
        return countPathsWithSum2(root,targetSum,array);
    }

    int countPathsWithSum2(Node node, int targetSum, ArrayList<Integer> array){
        if (node == null) return 0;
        int totalPaths = 0;
        addValue(array,node.data);
        totalPaths = countPaths(array,targetSum); // 배열방에서 검색함
        totalPaths += countPathsWithSum2(node.left,targetSum,array);
        totalPaths += countPathsWithSum2(node.right,targetSum,array);
        removeLast(array);
        return totalPaths;
    }

    void addValue(ArrayList<Integer> array, int value){ //기존의 배열방을 돌면서, 현재 노드의 값을 더하고, 해당 노드를 추가함
        for (int i =0; i < array.size(); i++){
            array.set(i,array.get(i) + value);
        }
        array.add(value);
    }

    void removeLast(ArrayList<Integer> array){ //돌아오면서, 배열값을 삭제하고, 삭제한 값만큼 전체 빼줌
        int value = array.remove(array.size() -1);
        for (int i = 0; i<array.size(); i++){
            array.set(i,array.get(i) - value);
        }
    }

    int countPaths(ArrayList<Integer> array, int targetSum){
        int totalPaths = 0;
        for (int i =0; i<array.size(); i++){
            if (targetSum == array.get(i)) totalPaths ++;
        }
        return totalPaths;
    }


}

public class TreeApply9 {
    public static void main(String[] args) {
        Tree6 tree = new Tree6(10);
        System.out.println(tree.countPathsWithSum(5));
        System.out.println(tree.countPathsWithSum2(5));
    }
}
