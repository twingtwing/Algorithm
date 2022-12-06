package practice.tree;

/**
 *  Inorder/Preorder/Postorder 방식으로 Tree를 구현
 *  => 1개를 가지고 경우의 수가 많기 때문에, 2개를 동시에 가지고 추측하여 Tree를 구현한다.
 *  - Preorder + Inorder : preorder은 맨앞이 root이고, Inorder은 중간쯤에 root가 있음
 *                         Inorder에서 root를 찾으면 양쪽은 서브트리가 된다. 다시 preorder의 2번째는 왼쪽 서브트리의 root가 된다.
 *                         그러면 다시 Inorder의 왼쪽의 서브트리의 다시 양쪽 서브 트리를 구현이 가능하다.
 *                         이런 방식을 반복하면, 구현이 가능하다.
 *  - Postorder + Inorder : 위에와 마찬가지이지만, 반대로 postorder은 맨 뒤가 root이다.  두번째는 오른쪽 노드의 root가된다.
 *                          이런 방식을 반ㅈ복하면 구현이 가능하다.
 *                          단, 다른점은 preorder에서 배열을 역순으로 하기 때문에 왼 -> 오 -> 중앙 으로 재귀호출 하지 않고,
 *                          중앙 -> 오 -> 왼 으로 재귀호출을 한다.
 *  - Preorder + Postorder : pre와 post는 구현이 가능하지만, 만약 full 이진트리가 아니고, 밸런스가 나쁘다면, 구현이 힘들다
 */
class BinaryTreeOrder{

    Node root;

    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){this.data = data;}
    }

}

public class Binary_Tree_Order {
    public static void main(String[] args) {

    }
}
