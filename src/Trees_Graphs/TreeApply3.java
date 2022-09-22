package Trees_Graphs;
/**
 * 응용 : 주어진 트리가 이진 검색트리인지 확인하시오 => inorder(왼 -> 중 -> 오른) 순회를 이용하면, 이진검색트리를 정렬해서 읽을 수 있음
*/
class Trees3 {
    class Node {
        int data;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    Node root;
    int size;

    Trees3(int size) {
        this.size = size;
        root = makeBST(0, size - 1);
        root.right.right.right.left = new Node(10); // 이진 검색 트리가 아님
        this.size++;
    }

    Node makeBST(int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1);
        node.right = makeBST(mid + 1, end);
        return node;
    }

    boolean isValidateBST1(){
        int [] ary = new int[size];
        inOrder(root,ary);
        for(int i = 1; i< ary.length; i++){
            if (ary[i] < ary[i-1]){
                return false;
            }
        }
        return true;
    }

    int index = 0;
    void inOrder(Node node, int [] ary){
        if (node == null) return;

        //왼
        inOrder(node.left,ary);
        //중앙
        ary[index] = node.data;
        index++;
        //오른쪽
        inOrder(node.right,ary);
    }

    // 배열을 사용하면서, 공간복잡도가 N이 증가하게 됨
    // 이를 대신해, 그 전의 값을 담을 공간 1개만 사용하여 공간복잡도를 줄임
    Integer last_printed = null; // 정수객체의 주소를 넘길 수 있도록, 클래스 타입으로 선언
    boolean isValidateBST2(){
        return isValidateBST2(root);
    }

    boolean isValidateBST2(Node node){
        if (node == null) return true;
        if (!isValidateBST2(node.left)) return false;
        if (last_printed != null && node.data < last_printed){
            return false;
        }
        last_printed = node.data;
        if (!isValidateBST2(node.right)) return false;
        return true;
    }

    // max, min값을 저장하여, 조건을 벗어나면 바로 false를 반환하는 방법 => 이때는 preorder방법을 이용한다.
    boolean isValidateBST3(){
        return isValidateBST3(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    boolean isValidateBST3(Node node, int min, int max){
        if (node == null) return true;
        if (node.data < min || node.data > max) return false;
        if (isValidateBST3(node.left, min, node.data) || !isValidateBST3(node.right,node.data,max)) return false;
        return true;
    }

}

public class TreeApply3 {
    public static void main(String[] args){
        Trees3 tree = new Trees3(10);
        System.out.println(tree.isValidateBST1());
        System.out.println(tree.isValidateBST2());
        System.out.println(tree.isValidateBST3());
    }
}
