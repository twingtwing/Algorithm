package practice.tree;

/**
 * 응용 1. 이진 검색트리 확인하는 알고리즘
 * 응용 2. 이진 검색 트리에서 주어진 노드의 다음 노드를 구하는 알고리즘
 */
class BinarySearchTree02{

    int size;
    Node root;

    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){this.data = data;}
    }

    BinarySearchTree02(int size){
        this.size = size;
        this.size = size;
        this.root = makeBST(1,size-1);
    }

    Node makeBST(int prev, int next){
        if (prev > next) return null;
        int mid = (prev + next)/2;
        Node result = new Node(mid);
        result.left = makeBST(prev,mid-1);
        result.right = makeBST(mid+1,next);
        return result;
    }

    // 응용 1.1 : inorder을 이용하여 배열 정렬
    // => 이진 검색 트리는 inorder로 배열을 생성하면 오름 차순 정렬을 얻을 수 있다.
    // 시간 복잡도 : O(n)
    boolean isValidateBST(){return isValidateBST(this.root);}

    boolean isValidateBST(Node node){
        if (node == null) return true;
        int [] ary = new int[this.size];
        inorderBST(node, ary);
        for (int i = 1; i < this.size; i++){
            if (ary[i-1] > ary[i]) return false;
        }
        return true;
    }

    int index = 0;
    void inorderBST(Node node, int [] ary){
        if (node == null) return;
        inorderBST(node.left,ary);
        ary[index++] = node.data;
        inorderBST(node.right,ary);
    }

    // 응용 1.2 : 배열 대신 변수에 할당 => 공간 복잡도를 줄임
    boolean isValidateBST2(){return isValidateBST2(this.root);}

    int prev = Integer.MIN_VALUE;
    boolean isValidateBST2(Node node){
        if (node == null) return true;
        inorderBST2(node,prev);
        return prev != Integer.MAX_VALUE;
    }

    void inorderBST2(Node node, int prev){
        if (node == null || prev == Integer.MAX_VALUE) return;
        inorderBST2(node.left,prev);
        prev = node.data > prev ? node.data : Integer.MAX_VALUE;
        inorderBST2(node.right,prev);
    }

    // 응용 1.2.2

    // 응용 1.3 : inorder이 아닌 preorder 이용

    // 응용 2.1

    // 응용 2.2
    
}


public class Binary_Tree_Apply02 {
    public static void main(String[] args) {
        
    }
}
