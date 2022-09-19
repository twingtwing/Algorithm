package Trees_Graphs;
/**
 * 배열로 Tree를 구현
 * 정렬이 되어있고, 고유한 정수로만 이루어진 배열이 있다. 이 배열로 이진 검색 트리를 구현하시오
 * 이진 검색 트리의 특징 : 왼쪽 < 오른쪽 이다.
 * 즉, 중간값이 root에 오게 되므로 배열의 중간값을 차례대로 구하면 된다.
 * 전체의 중간값을 구하고, 중간을 자른 양쪽 배열의 중간값을 구한다. 이 과정을 반복한다.
 * 이때, 짝수의 경우는 앞/뒤 쪽 중 어디를 중간값을 할것인지는 미리 정해둔다.??
 * 이 과정을 반복해서 거리를 좁혀나가면서, 데이터를 찾아나가는 구조,,?
 *
 */
class Trees{
    class Node{
        int data;
        Node right;
        Node left;
        Node(int data,Node right,Node left){
            this.data = data;
            this.right = right;
            this.left = left;
        }
    }

    void

}

public class TreeArray {
    public static void main(String [] args){

    }
}
