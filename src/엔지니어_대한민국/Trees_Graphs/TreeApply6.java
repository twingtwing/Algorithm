package 엔지니어_대한민국.Trees_Graphs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 응용 : 배열의 값을 왼쪽부터 하나씩 넣으면서 이진검색트리를 만들었다.
 *       주어진 이진검색트리를 만들 수 있는 모든 배열을 찾는 알고리즘 구현
 *       (단, 트리에 중복값업음)
 *       => 가장 왼쪽이 root라는 의미
 */
class Trees6{
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }
    
    Node root;
    
    Trees6(int size){
        root = makeBST(0, size - 1);
    }

    Node makeBST(int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1);
        node.right = makeBST(mid + 1, end);
        return node;
    }
}

public class TreeApply6 {
    public static void main(String[] args) {
        Trees6 tree = new Trees6(5);
        ArrayList<LinkedList<Integer>> result = allSequences(tree.root);
        for (LinkedList<Integer> list : result){
            for (int data : list){
                System.out.print(data + " ");
            }
            System.out.println();
        }

    }

    //이진검색트리를 만들수 있는 모든 배열을 가지고 오는 함수
    static ArrayList<LinkedList<Integer>> allSequences(Trees6.Node node){
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
        if (node == null){
            result.add(new LinkedList<Integer>()); //빈 list를 반환
            return result;
        }
        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(node.data);

        //양쪽 노드 서브 트리를 계속 만들어서 경우의 수를 늘림
        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

        //왼쪽, 오른쪽 경우의 수가 n:m으로 만들어지기 때문에 이중 for구문이 발생한다.
        for (LinkedList<Integer> left : leftSeq){
            for (LinkedList<Integer> right : rightSeq){
                //경우의 수를 담을 리스트
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveLists(left,right,weaved,prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }

    //경우의 수를 만들어주는 함수
    static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
                            ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix){
        // 둘중 하나라도 0이되면, 경우의 수는 1개밖에 남지 않기때문에 재귀호출을 멈춘다.
        if (first.size() == 0 || second.size() == 0){
            LinkedList<Integer> result = new LinkedList<Integer>();
            for (int data : prefix) result.add(data);
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return; //재귀 호출 종료
        }

        // 첫번째 경우의 수
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first,second,results,prefix);

        //첫번째 경우의 수 원복
        prefix.removeLast();
        first.addFirst(headFirst);

        //두번째 경우의수
        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first,second,results,prefix);

        //두번째 경우의 수 원복
        prefix.removeLast();
        second.addFirst(headSecond);

    }

}
