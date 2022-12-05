package practice.tree;

import java.util.HashMap;

/**
 * 응용 1. 이진 트리에서 주어진 2개의 첫번째 공통 부모를 찾는 알고리즘 (다른 자료구조 사용 불가)
 *       1) 아래에서부터 root까지의 길이를 재어 그 차이 값으로 계산
 *       2) 부모의 자식의 형제 노드 검색
 *       3) 부모 link field가 없는 경우, root에서 검색
 *       4) postorder
 *       5) 노드가 없을 경우를 방지
 */
class BinaryTree{

    Node root;
    HashMap<Integer, Node> nodeHashMap;

    class Node{
        int data;
        Node left;
        Node right;
        Node parent;
        Node(){}
        Node(int data){this.data = data;}
    }

    BinaryTree(int size){
        this.nodeHashMap = new HashMap<>();
        this.root = makeBST(1,size-1,null);
    }

    Node makeBST(int s, int e,Node parent) {
        if (s > e) return null;
        int m = (s+e)/2;
        Node node = new Node(m);
        node.left = makeBST(s,m-1,node);
        node.right = makeBST(m+1,e,node);
        node.parent = parent;
        this.nodeHashMap.put(m,node);
        return node;
    }

    Node getNode(int key){return this.nodeHashMap.get(key);}

    // 응용 1. 노드에서부터 root까지의 길이를 재어 그 차이 값을 통해 길이를 재조정하여 공통 부모 노드를 찾는 알고리즘
    // => 무조건 최고 level을 가진 노드를 기준으로 그 위에 조상 노드가 존재하기 때문
    Node commonAncestor(int one, int two){
        Node first = getNode(one);
        Node second = getNode(two);
        int diff = getLevel(first) - getLevel(second);
        if (diff > 0) first = getUpDiff(first,diff);
        else if(diff < 0) second = getUpDiff(second,-diff);
        return findParent(first,second);
    }

    int getLevel(Node node){
        if (node == null) return 0;
        int sum = 0;
        while(node != null){
            node = node.parent;
            sum ++;
        }
        return sum;
    }

    Node getUpDiff(Node node, int diff){ // 같은 level dp
        while(diff > 0 && node != null){
            node = node.parent;
            diff --;
        }
        return node;
    }

    Node findParent(Node first, Node second){
        if (first == null || second == null) return null;
        while(first != second && first != null && second != null){
            first = first.parent;
            second = second.parent;
        }
        return first == null || second == null ? null : first;
    }
    
    // 방법 2. 부모의 자식의 형제 노드를 검색
    // 형제노드의 하위노드에 해당 노드가 존재한다면, 바로 부모노드가 공동 조상노드인 점을 활용
    Node commonAncestor2(int one, int two){
        Node first = getNode(one);
        Node second = getNode(two);

        // root아래에 없는 경우 미리 제회
        if (!coversAncestor(this.root,first) || !coversAncestor(this.root,second)) return null;
        //각 노드가 공통 조상 노드인경우 미리 제회
        else if(coversAncestor(first,second)) return first;
        else if(coversAncestor(second,first)) return second;
        
        Node sibling = getSibling(first);
        Node parent = first.parent;

        while(!coversAncestor(sibling,second)){
            sibling = getSibling(parent);
            parent = parent.parent;
        }

        return parent;
    }
    
    boolean coversAncestor(Node parent, Node child){
        if (parent == null || child == null) return false;
        if (parent == child) return true;
        return coversAncestor(parent.left,child) || coversAncestor(parent.right,child);
    }
    
    Node getSibling(Node node){
        if (node == null || node.parent == null) return null;
        Node parent = node.parent;
        return parent.left == node ? parent.right : parent.left;
    }
    
    // 방법 3. 부모 link field 가 없는 경우, root 에서부터 시작
    // root에서 부터 시작해서 서브트리에 존재하는지 확인
    Node commonAncestor3(int one, int two){
        Node first = getNode(one);
        Node second = getNode(two);

        if (!coversAncestor(this.root,first)||!coversAncestor(this.root,second)) return null;

        return subAncestor(this.root, first, second);
    }

    Node subAncestor(Node root,Node first,Node second){
        if (root == null || first == null || second == null) return null;

        boolean fristInLeft = coversAncestor(root.left,first);
        boolean secondInLeft = coversAncestor(root.left,second);

        if (fristInLeft !=  secondInLeft) return root;

        Node parent = fristInLeft ? root.left : root.right;
        return subAncestor(parent,first,second);
    }
    
    // 방법 4. postorder : 자식 노드를 모두 확인하고 나서, 부모 노드를 확인한다.
    // 아직도 이해안됨,,,,
    Node commonAncestor4(int one, int two){
        Node first = getNode(one);
        Node second = getNode(two);
        return commonAncestor4(this.root, first, second);
    }

    Node commonAncestor4(Node root, Node first, Node second){
        if (root == null) return null;
        if (root == first && root == second) return root;

        Node left = commonAncestor4(root.left,first,second);
        // 왼쪽 끝까지 갈때까지 해당 노드를 못 찾으면, 계속 왼쪽 서브트리를 찾음
        if (left != null && left != first && left != second) return left;

        Node right = commonAncestor4(root.right,first,second);
        // 오른쪽 끝까지 갈때까지 해당 노드를 못 찾으면, 계속 오른쪽 서브트리를 찾음
        if (right != null && right != first && right != second) return right;

        // null이거나 값을 찾아서 내려옴
        // => 둘다 null이 아니거나 둘 중 하나가 root이면 root 출력
        if ((left != null && right != null) ||
                (root == first || root == second)) return root;

        // 둘 중 하나가 null 이면, null이 아닌 값을 출력
        return left == null ? right : left;
    }
    
    // 방법 5. postorder + pointer
    // 찾음 유무를 객체로 저장하여 해당 포인터로 반환
    // => 아직 이해 안됨
    class Result{
        Node node;
        boolean isAncester;
        
        Result(Node node, boolean isAncester){
            this.node = node;
            this.isAncester = isAncester;
        }
    }
    
    Node commonAncestor5(int one, int two){
        Node first = getNode(one);
        Node second = getNode(two);
        
        Result result = commonAncestor5(this.root, first, second);
        
        return result.isAncester ? result.node : null;
    }
    
    Result commonAncestor5(Node root, Node first, Node second){
        if (root == null) return new Result(null, false);
        if (root == first && root == second) return new Result(root, true);
        
        // 왼쪽에 찾을때까지 이동
        Result left = commonAncestor5(root.left, first, second);
        if (left.isAncester) return left;
        
        // 오른쪽 까지 찾을때까지 이동
        Result right = commonAncestor5(root.right, first, second);
        if (right.isAncester) return right;
        
        if(left.node != null && right.node != null) 
            return new Result(root, true);
        else if(root == first || root == second)
            return new Result(root, left.node != null || right.node != null);
        
        return new Result(left.node != null ? left.node : right.node, false);
    }
    

}

public class Binary_Tree_Apply03 {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(10);
        System.out.println(tree.commonAncestor(2,9).data);
        System.out.println(tree.commonAncestor2(2,9).data);
        System.out.println(tree.commonAncestor3(2,9).data);
        System.out.println(tree.commonAncestor4(2,9).data);
        System.out.println(tree.commonAncestor5(2,9).data);

    }
}
