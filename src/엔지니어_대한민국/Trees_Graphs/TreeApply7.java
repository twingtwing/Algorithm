package 엔지니어_대한민국.Trees_Graphs;
/**
 * 응용 : 크기가 매우 큰 두개의 이진트리 가 있다. 작은 트리가 큰 트리의 서브ㄹ리인지 확인하는 알고리즘 구현
 *       => 작은 트리 루트노드의 값이 큰 트리에 존재하고, 그 노드를 기준으로 줄기를 잘라냈을때,
 *          작은 트리의 값과 구조가 같으면 서브트리로 인정함
 *       => preorder 방식 : 기준값이 root이기 때문에 자기자신을 가장 먼저 순회하는 preorder방식으로 해야한다.
 */
class Trees5{
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }

    Node root;

    Node makeBST(int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1);
        node.right = makeBST(mid + 1, end);
        return node;
    }

    // preorder 방식
    boolean containsTree(Node t1, Node t2){
        if (t2 == null) return true; //검색할 필요가 없는 부분을 미리 확인하여 반환해주면, 시간 절약 가능
        return subTree(t1,t2);
    }

    // t1을 preorder 순회를 하면서 root값과 같은 값을 가진 node를 찾는 재귀함수
    boolean subTree(Node t1, Node t2){
        if (t1 == null) {
            return false;
        }else if(t1.data == t2.data && maktchTree(t1,t2)){
            return true;
        }
        //위의 조건을 만족하지 않으면, 계속 재귀함수를 이어간다.
        return subTree(t1.left,t2) || subTree(t1.right,t2);
    }

    //두개의 트리를 똑같이 순회하면서 모두의 값이 일치하는지 확인
    boolean maktchTree(Node t1, Node t2){
        if (t1 == null && t2 == null){
            return true;
        }else if (t1 == null || t2 == null){
            return false;
        }else if (t1.data != t2.data){
            return false;
        }else{
            return maktchTree(t1.left,t2.left) && maktchTree(t1.right, t2.right);
        }
    }
}

public class TreeApply7 {
    public static void main(String[] args) {
        Trees5 tree1 = new Trees5();
        Trees5 tree2 = new Trees5();
        tree1.root = tree1.makeBST(0,9);
        tree2.root = tree2.makeBST(5,9);

        System.out.println(tree1.containsTree(tree1.root,tree2.root));

        Trees5 tree3 = new Trees5();
        tree3.root = tree3.makeBST(7,9);

        System.out.println(tree1.containsTree(tree1.root,tree3.root));
    }
}
