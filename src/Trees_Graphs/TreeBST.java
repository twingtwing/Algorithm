package Trees_Graphs;

/**
 * BST Search : 이진 검색트리는 왼 < 오 규칙을 지키기 때문에 검색하기 쉽다(시간복잡도 : logN)
 *  - BST Insertion (삽입)
 *      : logN의 시간이 걸림
 *  - BST Deletion  (삭제)
 *      : logN의 시간이 걸림
 *        경우의 수가 총 3개(no child, one child, two child)
 *          no child => 그냥 부모 노드와 link를 끊어 버리면 됨(Java는 가비지 컬렉션???이 있기때문에 그냥 끊어버리면됨)
 *          one child => child를 대신 부모노드와 linke를 연결하고 자기 자신은 부모노드와 연결을 끊으면 됨 (child랑은 안끊음)
 *          two child => 양 쪽 서브트리의 값을 조회한다. 왼쪽 서브트리에서 최대값을 가져오거나 오른쪽 서브트리에서는 최소값을 구해온다.
 *                       왼족 서브트리에서 오른쪽을 타고 내려가서 가장 level이 높고 자식이 없는 노드가 최댓값이고,
 *                       오른쪽 서브트리에서 왼족을  타고 내려가서 가장 level이 높고 자식이 없는 노드가 최솟값이다.
 *                       이렇게 구한 값을 삭제한 노드에 대신한다. 구한값을 가져올때는 no child때처럼 그냥 연결을 끊기만 하면된다.
 */
class Trees1{
    class Node{
        int data;
        Node left, right;
        Node(int data){
            this.data = data;
        }
    }

    Node root;

    public Node search(Node node, int key){
        if (node == null || node.data == key) return node;
        if (node.data > key) return search(node.left, key);
        return search(node.right, key);
    }

    public void insert(int data){
        root = insert(root,data);
    }

    private Node insert(Node node,int data){
        if (node == null) return new Node(data);
        if (node.data < data) return insert(node.left, data);
        return insert(node.right,data);
    }

    public void delete(int data){

    }
}

public class TreeBST {
    public static void main(String[] args) {

    }
}
