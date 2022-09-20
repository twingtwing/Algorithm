package Trees_Graphs;

import java.util.Map;

/**
 * 배열로 Tree를 구현
 * 정렬이 되어있고, 고유한 정수로만 이루어진 배열이 있다. 이 배열로 이진 검색 트리를 구현하시오
 * 이진 검색 트리의 특징 : 왼쪽 < 오른쪽 이다.
 * 즉, 중간값이 root에 오게 되므로 배열의 중간값을 차례대로 구하면 된다.
 * 전체의 중간값을 구하고, 중간을 자른 양쪽 배열의 중간값을 구한다. 이 과정을 반복한다.
 * 이때, 짝수의 경우는 앞/뒤 쪽 중 어디를 중간값을 할것인지는 미리 정해둔다.??
 * 이 과정을 반복해서 거리를 좁혀나가면서, 데이터를 찾아나가는 구조,,?
 * 시간 복잡도 (log n)
 *
 */
class Trees{
    class Node{
        int data; //중간값
        Node right; //큰쪽
        Node left; //작은쪽
        Node(int data,Node left,Node right){
            this.data = data;
            this.right = right;
            this.left = left;
        }
    }

    Node root;

    //재귀호출을 이용해 이진 검색 트리 구현
    void madeSearch(int [] ary){
        root = madeSearch(ary, 0, ary.length -1);
    }

    Node madeSearch(int [] ary, int start, int end) {
        //재귀 호출 종결 지점
        if (start > end) return null;

        //중간 index
        int middle = (end + start) / 2;
        //왼쪽 재귀호출
        Node left = null;
        if (start != middle){
            left = madeSearch(ary,start,middle-1);
        }
        //오른쪽 재귀호출
        Node right = null;
        if (end != middle){
            right = madeSearch(ary,middle + 1,end);
        }

        Node node = new Node(ary[middle],left,right);
        return node;
    }

    //재귀호출을 이용해 이진 검색 트리 검색 구현
    public void searchBNode(int find){
        searchBNode(root,find);
    }

    public void searchBNode(Node n, int find){
        if (n == null)return;//재귀 호출은 끝나는 지점이 중요

        if (n.data > find){
            System.out.println("No Find " + find + " : "+ n.data);
            searchBNode(n.left, find);
        }else if(n.data < find){
            System.out.println("No Find " + find + " : "+ n.data);
            searchBNode(n.right, find);
        }else{
            System.out.println("Find "+ n.data);
        }
    }

    public void inorder(){
        inorder(root);
        System.out.println();
    }

    public void inorder(Node node){
        if (node != null){
            inorder(node.left);
            System.out.print(node.data + "\t");
            inorder(node.right);
        }
    }

    public void preorder(){
        preorder(root);
        System.out.println();
    }

    public void preorder(Node node){
        if (node != null){
            System.out.print(node.data + "\t");
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void postorder(){
        postorder(root);
        System.out.println();
    }

    public void postorder(Node node){
        if (node != null){
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + "\t");
        }
    }
}

public class TreeArray {
    public static void main(String [] args){
        Trees tree = new Trees();
        int [] ary = {0,1,2,3,4,5,6,7,8,9};
        tree.madeSearch(ary);
        tree.preorder();
        tree.inorder();
        tree.postorder();
        tree.searchBNode(9);
    }
}
