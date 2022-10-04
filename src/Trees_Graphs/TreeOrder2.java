package Trees_Graphs;

/**
 * Inorder/Preorder/Postorder 방식으로 Tree를 구현
 * => Preorder + Inorder / Postorder + Inorder / Preorder + Postorder => 2개를 동시에 가지고 추측하여 Tree를 구현한다.
 */
class Tree4{
    class Node{
        int data;
        Node left, right;
        Node(int data){
            this.data = data;
        }
    }

    Node root;
    static  int pIndex =  0; // 배열을 어디까디 읽었는지 확인하는 변수

    // Preorder + Inorder
    public  void  buildTreeByInPre(int in[], int pre[]){
        pIndex = 0;
        root = buildTreeByInPre(in,pre,0,in.length -1);
    }

    private Node buildTreeByInPre(int in[], int pre[], int start, int end){
        if (start > end) return  null;
        Node node = new Node(pre[pIndex++]);
        if (start == end) return node; // 서브트리가 자기자신뿐이라는 의미
//        int mid = search

    }

    // Inorder + Postorder

    // Preorder + Postorder

}

public class TreeOrder2 {
    public static void main(String[] args) {

    }
}
