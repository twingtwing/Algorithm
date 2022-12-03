package practice.tree;

import java.util.Random;

/**
 * 응용 1. 이진 검색트리 확인하는 알고리즘
 * 응용 2. 이진 검색 트리에서 주어진 노드의 다음 노드를 구하는 알고리즘(순서 : inorder)
 * 응용 3. 이진 검색 트리에서 임의의 노드를 가져오는 알고리즘(노드에 추가정보 저장 가능)
 */
class BinarySearchTree02{

    int size;
    Node root;

    class Node{
        int data;
        int total; //양쪽 서브트리 갯수
        Node left;
        Node right;
        Node parent;
        Node(int data){
            this.data = data;
            this.total = 1;
        }
    }

    BinarySearchTree02(){}

    BinarySearchTree02(int size){
        this.size = size;
        this.root = makeBST(1,size,null);
//        this.root.left.left.data = 11;
    }

    Node makeBST(int prev, int next, Node parent){
        if (prev > next) return null;
        int mid = (prev + next)/2;
        Node result = new Node(mid);
        result.left = makeBST(prev,mid-1,result);
        result.right = makeBST(mid+1,next,result);
        result.parent = parent;
        return result;
    }

    Node getNode(int data){
        return searchBST(this.root,data);
    }

    // 이진 검색 트리에서 검색은 순회 대신, 검색을 사용하면 된다.
    Node searchBST(Node node,int data){
        if (node == null) return null;
        if (node.data == data) return node;
        if (node.data > data) return searchBST(node.left,data);
        return searchBST(node.right,data);
    }

    // 응용 1.1 : inorder을 이용하여 배열 정렬
    // => 이진 검색 트리는 inorder로 배열을 생성하면 오름 차순 정렬을 얻을 수 있다.
    // 시간 복잡도 : O(n)
    boolean isValidateBST(){return isValidateBST(this.root);}

    boolean isValidateBST(Node node){
        if (node == null) return true;
        int [] ary = new int[this.size];
        inorderBST(node, ary);
        for (int i = 1; i < ary.length; i++) if (ary[i-1] > ary[i]) return false;
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

    Integer prev = Integer.MIN_VALUE;
    boolean isValidateBST2(Node node){
        if (node == null) return true;
        inorderBST2(node);
        return prev != Integer.MAX_VALUE;
    }

    void inorderBST2(Node node){
        if (node == null || prev == Integer.MAX_VALUE) return;
        inorderBST2(node.left);
        prev = node.data > prev ? node.data : Integer.MAX_VALUE;
        inorderBST2(node.right);
    }

    // 응용 1.2.2
    boolean isValidateBST22(){return isValidateBST22(this.root);}

    Integer prevNum = Integer.MIN_VALUE;
    boolean isValidateBST22(Node node){
        if (node == null) return true;
        if (!isValidateBST22(node.left)) return false;
        if (prevNum > node.data) return false;
        prevNum = node.data;
        return isValidateBST22(node.right);
    }

    // 응용 1.3 : preorder => Max,Min 값을 저장
    boolean isValidateBST3(){return isValidateBST22(this.root,Integer.MIN_VALUE,Integer.MAX_VALUE);}

    boolean isValidateBST22(Node node, int min, int max){
        if (node == null) return true;
        if (min > node.data || max < node.data) return false;
        if(!isValidateBST22(node.left,min,node.data)) return false;
        return isValidateBST22(node.right, node.data, max);
    }

    // 응용 2 : Node에 추가적인 저장공간을 준다.
    Node findNext(Node node){
        if (node == null) return null;

        // inorder 방식이기 때문에 해당노드의 오른쪽으로 가야한다.
        if (node.right == null) return findAbove(this.root, node);
        else return findBelow(node.right);
    }

    Node findAbove(Node node, Node child){
        if (node == null) return null;
        if (node.left == child) return node;
        return findAbove(node.parent, node);
    }

    Node findBelow(Node node){
        if (node.left == null) return node;
        return findBelow(node.left);
    }

    // 응용 3.1 : BST 검색 연산 활용
    // => 단점 : 동일한 확률로 찾이 못함
    Node findNode(int data){
        return findNode(this.root, data);
    }

    Node findNode(Node node, int data){
        if(node == null) return null;
        else if (node.data == data) return node;
        else if(node.data > data) return findNode(node.left,data);
        else return findNode(node.right,data);
    }

    // 응용 3.2 : 순회하면서 카운트하여 해당 숫자가 되면 반환
    // 또한, 노드의 갯수를 미리 알고, 하위 서브 트리 갯수를 미리알면, 굳이 양쪽 서브트리를 순회할 필요가 없어짐
    void insert(int data){ //재귀호출을 통해 각각의 노드의 서브트리 길이를 저장
        this.root = insert(this.root,data);
    }

    Node insert(Node node, int data){
        if (node == null) return new Node(data);
        node.total++;
        if (node.data > data) node.left = insert(node.left,data);
        else if(node.data < data)node.right = insert(node.right,data);
        return node;
    }

    Node findRandom(){
        if (this.root == null) return null;
        int ran = new Random().nextInt(this.root.total);
        System.out.println(ran + " 숫자");
        return getItNode(this.root,ran);
    }

    // 서브트리의 갯수로 data의 위치를 추측한다.
    // 서브트리의 갯수로 추측이 가능한 이유는 이 트리가 이진 검색 트리 이기 때문이다.
    Node getItNode(Node node, int num) {
        if (node == null) return null;

        // 오른쪽 서브트리갯수로 오른쪽 숫자를 예측이 불가능하므로 왼쪽 서브트리 갯수만 구한다.
        // 즉 왼쪽 서브트리의 총 노드 갯수 + 왼쪽노드 이다.
        int left = node.left == null ? 0 : node.left.total;

        // (왼쪽 서브트리 갯수 + 왼쪽노드)가 data보다 크면 무조건 있기 때문에 이동
        if (num < left) return getItNode(node.left,num);
        // data에 (왼쪽 서브트리 갯수 + 준간노드)만큼 확실히 있고, 중간노드 까지 합해서 left + 1 빼주고 넘겨준다.
        else if (num > left) return getItNode(node.right, num - (left + 1) );
        // 왼쪽 서브트리 갯수 + 중간노드와 갯수와 일치 하므로 중간노드가 구하고자 하는 노드라는 의미
        else return node;
    }

}


public class Binary_Tree_Apply02 {
    public static void main(String[] args) {
        BinarySearchTree02 tree = new BinarySearchTree02(10);
        System.out.println(tree.isValidateBST());
        System.out.println(tree.isValidateBST2());
        System.out.println(tree.isValidateBST22());
        System.out.println(tree.isValidateBST3());

        System.out.println(tree.findNext(tree.getNode(8)).data);

        BinarySearchTree02 tree2 = new BinarySearchTree02();
        tree2.insert(4);
        tree2.insert(0);
        tree2.insert(1);
        tree2.insert(2);
        tree2.insert(5);
        tree2.insert(7);
        tree2.insert(8);
        tree2.insert(3);
        tree2.insert(6);
        tree2.insert(9);
        System.out.println(tree2.findNode(5).data);
        System.out.println(tree2.findRandom().data);
    }
}
