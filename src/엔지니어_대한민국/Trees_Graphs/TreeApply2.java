package 엔지니어_대한민국.Trees_Graphs;
/**
 * Tree 응용 : 주어진 이진트리의 balance가 맞는지 확인하는 함수를 구현하시오.
 *            (여기서 balance가 맞다는 의미는 어떤 노드의 양쪽 서브트리의 길이가 1이상 차이가 나지 않는것을 의미)
 */
class Tree3{
    class Node{
        int data;
        Node left = null;
        Node right = null;
        Node(int data){
            this.data = data;
        }
    }

    Node root;

    Tree3(int size){
        root = makeBST(0,size -1);
//        root.right.right.right.right = new Node(10); // unbalcned
    }

    Node makeBST(int start, int end){
        if (start > end) return null;
        int mid = (start + end)/2;
        Node node= new Node(mid);
        node.left = makeBST(start,mid-1);
        node.right = makeBST(mid+1,end);
        return node;
    }

    // balance가 맞는지 확인하는 함수
    boolean isBalanced(Node node){
        if (node == null) return true;
        int heightDiff = getHeight(node.left) - getHeight(node.right);
        if (Math.abs(heightDiff)>1){
            return false;
        }else{
            return isBalanced(node.left) && isBalanced(node.right);
        }
    }

    int getHeight(Node node){
        //호출 될때마다 매번 다시가서 길이를 재기 때문에 o(nlogn)의 시간이 걸림
        if (node == null) return -1;
        return Math.max(getHeight(node.left),getHeight(node.right))+1;
    }


    //이를 방지하기 위해 false대신 Integer.MIN_VALUE로 취급하면 바로 반활할수 있어 한번만 돌 수 있음 o(n)
    int checkHeight(Node node){
        if (node == null) return -1;
        int leftHeight = checkHeight(node.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int rightHeight = checkHeight(node.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff)>1){
            return Integer.MIN_VALUE;
        }else{
            return Math.max(leftHeight,rightHeight)+1;
        }
    }

    boolean isBalanced2(Node node){
        return checkHeight(node) != Integer.MIN_VALUE;
    }

    //balance를 재정의 : 그 어떤 서브트리도 길이가 1 이상 차이가 나면 안된다.
    //원래는 나오면서 숫자를 세어갔지만, 이제는 들어가면서 숫자를 세어감
    class Level{
        int min = Integer.MAX_VALUE; //최솟값은 가장 큰값으로
        int max = Integer.MIN_VALUE; //최갯값은 가장 작은값으로
        // 어떤값이랑 비교해도 새로운 값으로 엡데이트 되도록함
    }

    boolean isBalanced3(Node node){
        Level obj = new Level();
        checkHeight(node,obj,0);
        if (Math.abs(obj.min - obj.max)>1) return false;
        else return true;
    }

    void checkHeight(Node node, Level obj, int level){
        if (node == null){
            if (level < obj.max) obj.min = level;
            else if(level > obj.max) obj.min = level;
            return;
        }
        checkHeight(node.left,obj,level + 1);
        checkHeight(node.right,obj,level + 1);
    }

}

public class TreeApply2 {
    public static void main(String [] args){
        Tree3 tree = new Tree3(10);
        System.out.println(tree.isBalanced(tree.root));
        System.out.println(tree.isBalanced2(tree.root));
    }
}
