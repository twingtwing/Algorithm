package Trees_Graphs;

import java.util.Random;

/**
 * 응용 : 이진 검색트리에서 임의의 노드를 가져오는 알고리즘을 구현
 *       단, 모든 노드에 동일한 확률로 임의의 노들르 가져와야 하며, 필요한 경우 노드에 추가 정보를 저장하여도 가능
 *       => 동일한 확률과 가장 적은 순회를 하는 효율적인 방법을 해야한다.
 *       => 임의 숫자(노드의 갯수보다 적은)를 구하여, 순회(pre,in,post)하면서 카운트하여 해당 숫자가 되면, 현재 위치 노드를 반환한다.
 *       => 여기서 각각의 노드의 하위 서브트리 갯수를 알고 있다면, 굳이 양쪽 서브트리를 순회할 필요가 없어지기에, 시간을 더 절약할 수 있음
 *       => 즉, 저장된 서브트리 갯수를 사용하여 숫자계산을 통해 순회를 줄일수 있음
 */
class Tree5{
    class Node{
        int data;
        int size = 0;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            this.size = 1;
        }

        // tree node insert
        void insert(int data){
            // 이진 검색 트리 이기 때문
            if (data <= this.data){
                if (left == null){
                    left = new Node(data);
                }else{
                    left.insert(data); //왼쪽으로 재귀호출
                }
            }else{
                if (right == null){
                    right = new Node(data);
                }else{
                    right.insert(data); //오른쪽으로 재귀호출
                }
            }
            size ++;
        }

        int size(){return size;}

        Node find(int data){
            if (data == this.data){
                return this;
            }else if (data < this.data){
                return left != null? left.find(data) : null;
            }else if (data > this.data){
                return right != null? right.find(data) : null;
            }else{
                return null;
            }
        }

        // Random으로 찾을 index
        Node getItNode(int i){
            int leftSize = left == null ? 0 : left.size();
            if (i < leftSize){
                return left.getItNode(i);
            }else if(i == leftSize){
                return this;
            }else{
                return right.getItNode(i - (leftSize + 1)); // left + root 갯수 만큼 빼줌
            }
        }
    }

    Node root;

    int size(){
        return root == null? 0 : root.size();
    }

    void insert(int data){
        if (root == null) root = new Node(data);
        else root.insert(data);
    }

    // 임의 노드를 가져오는 Random 함수
    Node getRandomNode(){
        if (root == null) return null;
        Random random = new Random();
        int i = random.nextInt(size()); // 전체 노드갯수를 넘어가지 못하도록 한정함
        return root.getItNode(i);
    }

}

public class TreeApply8 {
    public static void main(String[] args) {
        Tree5 tree = new Tree5();
        tree.insert(4);
        tree.insert(0);
        tree.insert(1);
        tree.insert(2);
        tree.insert(5);
        tree.insert(7);
        tree.insert(8);
        tree.insert(3);
        tree.insert(6);
        tree.insert(9);
        System.out.println(tree.getRandomNode().data);
    }
}
