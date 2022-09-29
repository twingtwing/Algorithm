package Trees_Graphs;
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
        int count;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }

    Node root;

}

public class TreeApply8 {
    public static void main(String[] args) {

    }
}
