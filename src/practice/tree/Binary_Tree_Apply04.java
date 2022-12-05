package practice.tree;

/**
 * 응용 1 : 주어진 이진검색트리를 만들 수 있는 모든 배열을 찾는 알고리즘 구현(단, 트리에 중복값업음)
 *        (배열은 가장 왼쪽이 root이다.)
 * 응용 2 : 작은트리가 큰트리의 서브트리로 속하는지 알아보는 알고리즘 (단, 트리에 중복값업음)
 */
class BinarySearch{

    Node root;

    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){this.data = data;}
    }

    BinarySearch(int size){this.root = makeBST(1,size-1);}

    Node makeBST(int s, int e){
        if (s > e) return null;
        int m = (s+e)/2;
        Node node = new Node(m);
        node.left = makeBST(s,m-1);
        node.right = makeBST(m + 1,e);
        return node;
    }

}

public class Binary_Tree_Apply04 {
    public static void main(String[] args) {

    }
}
