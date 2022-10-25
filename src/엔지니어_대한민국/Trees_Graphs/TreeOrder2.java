package 엔지니어_대한민국.Trees_Graphs;

/**
 * Inorder/Preorder/Postorder 방식으로 Tree를 구현
 * => 1개를 가지고 경우의 수가 많기 때문에, 2개를 동시에 가지고 추측하여 Tree를 구현한다.
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
        pIndex = 0; // preorder은 root가 맨 처음에 있기 때문에 처움부터 시작
        root = buildTreeByInPre(in,pre,0,in.length -1);
    }

    private Node buildTreeByInPre(int in[], int pre[], int start, int end){
        if (start > end) return  null;
        Node node = new Node(pre[pIndex++]);
        if (start == end) return node; // 서브트리가 자기자신뿐이라는 의미 호출한 노드가 없음
        int mid = search(in,start,end,node.data); // 해당 node data을 담은 방번호를 가지고옴
        node.left = buildTreeByInPre(in,pre,start,mid - 1); // 방번호를 기준으로 첨부터 앞까지(inorder이기에?)
        node.right = buildTreeByInPre(in,pre,mid +1,end); // 방번호를 기준으로 뒤에서 부터 끝까지(inorder이기에?)
        return  node;
    }

    // Inorder + Postorder
    public void buildTreeByInPost(int in[],int post[]){
        pIndex = post.length -1; // postorder은 root가 맨 끝에 있기 때문에 끝부터 시작
        root = buildTreeByInPost(in,post,0,in.length -1);
    }

    public Node buildTreeByInPost(int in[],int post[],int start, int end){
        if (start > end) return null;
        Node node = new Node(post[pIndex--]); //postorder이기때문에 뒤에서 부터 시작
        if (start == end) return node;
        int mid = search(in,start,end,node.data);
        node.right = buildTreeByInPost(in,post,mid +1,end); //오른쪽 서브트리를 먼저 부른 이유 : postorder 역순이기 때문
        node.left = buildTreeByInPost(in,post,start,mid - 1);
        return node;
    }

    //해당 배열을 start부터 end사이의 해당 data있으면, 해당 방번호를 가지고 옴?
    private int search(int ary[], int start, int end, int val){
        int i;
        for (i = start; i<= end; i++) if (ary[i] == val) return i;
        return i;
    }

    // 출력
    public void printInorder(){
        printInorder(root);
    }

    public void printInorder(Node node){
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

}

public class TreeOrder2 {
    public static void main(String[] args) {
        Tree4 tree = new Tree4();
        int [] pre = {4,2,1,3,6,5,7};
        int [] in = {1,2,3,4,5,6,7};
        int [] post = {1,3,2,5,7,6,4};
        tree.buildTreeByInPre(in,pre);
        tree.printInorder();
        System.out.println();
        tree.buildTreeByInPost(in,post);
        tree.printInorder();
        System.out.println();
    }
}
