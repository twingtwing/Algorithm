package 엔지니어_대한민국.Trees_Graphs;

import java.util.HashMap;

/**
 * 응용 : 이진트리에서 주어진 2개의 노드의 첫번째 공통된 부모를 찾으시오
 *       (단, 다른 자료구조는 사용금지)
 *       - 아래에서부터 길이를 재어서 차이 값을 계산
 *       - 부모의 자식의 형제노드 검색
 *       - 부모 주소가 없을 경우, root에서 검색
 *       - postorder을 통해 모든 노드를 딱 한번만 조회하는 최적화방법
 *       - 위의 방법의 단점이 tree안에 해당 노드들이 없는경우에도 찾은노드인지 공통부모인지 알수가 없음
 *          그렇기에 이를 명확히 해주는 방법
 */
class Tree1{
    static class Node{
        int data;
        Node left;
        Node right;
        Node parent;
        Node(int data){
            this.data = data;
        }
    }

    Node root;
    HashMap<Integer,Node> rootMap; //값으로 root를 찾기 쉽도록 map을 선언

    Tree1(int size){
        rootMap = new HashMap<Integer,Node>();
        root = makeBST(0, size - 1,null);
    }

    Node makeBST(int start, int end, Node parent) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1,node);
        node.right = makeBST(mid + 1, end,node);
        node.parent = parent;
        rootMap.put(mid,node);
        return node;
    }

    Node getNode(int data){
        return rootMap.get(data);
    }

    //방법1. 아래에서부터 길이를 재어서 그 차이값을 통해 길이를 재조정하여 공통 부모 노드를 참기
    Node commonAncestor(int d1, int d2){
        Node p  =getNode(d1);
        Node q = getNode(d2);
        int diff = depth(p) - depth(q);
        Node first = diff > 0 ? q : p ;
        Node second = diff > 0 ? p : q ;
        second = goUpBy(second,Math.abs(diff)); //level이 일치하도록 길이 재조정
        while(first!=second && first != null && second != null){
            first = first.parent;
            second = second.parent;
        }
        return first == null || second == null ? null : first;
    }

    Node goUpBy(Node node, int diff){
        // 차이만큼 위로 올라감
        while(diff > 0 && node != null){
            node = node.parent;
            diff --;
        }
        return node;
    }

    int depth(Node node){
        int depth = 0;
        while(node != null){
            node = node.parent;
            depth ++;
        }
        return depth;
    }

    //방법 2. 부모의 자식의 형제노드 검색
    boolean covers(Node parent, Node node){ //parent의 자손인지를 확인(재귀호출)
        if (parent==null) return false;
        if (parent == node) return true;
        return covers(parent.left, node) || covers(parent.right,node);
    }

    Node commonAncestor2(int d1, int d2){
        Node p = getNode(d1);
        Node q = getNode(d2);
        if(!covers(root,p) || !covers(root,q)){ //p와 q의 존재여부확인
            return null;
        }else if(covers(p,q)){
            return p;
        }else if(covers(q,p)){
            return q;
        }

        Node sibling = getSibling(p); // 형제 노드를 가지고옴
        Node parent = p.parent;
        while(!covers(sibling,q)){ //q가 형제노드 하위트리에 존재하는지 확인
            sibling = getSibling(parent);
            parent = parent.parent;
        }
        return parent;
    }

    Node getSibling(Node node){
        if (node == null || node.parent == null){
            return null;
        }
        Node parent = node.parent;
        return parent.left == node? parent.right : parent.left;
    }

    //방법 3. 부모주소가 없는 경우, root에서부터 내려와서 서브트리에 존재하는지 확인
    Node commonAncestor3(int d1, int d2){
        Node p = getNode(d1);
        Node q = getNode(d2);
        if (!covers(root,p) || !covers(root,q)){
            return null;
        }
        return ancestorHalper(root,p,q);
    }

    Node ancestorHalper(Node root, Node p, Node q){
        if (root == null || root == p || root == q){
            return root;
        }
        //이미 위에서 p,q가 트리안에 존재하는지 확인하였기 때문에,
        //왼족에 없으면 오른쪽에 있으므로, 굳이 오른쪽을 확인할 필요가 없다.
        boolean pIsOnLeft = covers(root.left,p);
        boolean qIsOnLeft = covers(root.left,q);
        if (pIsOnLeft != qIsOnLeft){
            return root;
        }
        Node childSide = pIsOnLeft? root.left : root.right;
        return ancestorHalper(childSide,p,q);
    }

    //방법 4. postorder을 통해 딱한번만
    //postorder은 왼쪽,오른쪽을 모두 조회하고 자기자신을 조회하기 때문에
    //즉, 자식노드를 모두 확인하고 부모노드를 확인함
    Node commonAncestor4(int d1, int d2){
        Node p = getNode(d1);
        Node q = getNode(d2);
        return commonAncestor4(root,p,q);
    }

    Node commonAncestor4(Node node, Node p, Node q){
        if (node == null) return null;
        if (node == p && node == q) return node;
        Node x = commonAncestor4(node.left,p,q); // 왼
        if (x != null && x != p && x != q){
            return x; //공통부모를 탖았다는 의미?
        }
        Node y = commonAncestor4(node.right,p,q); //오른
        if (y != null && y != p && y != q){
            return y;
        }
        if (x != null && y !=null){ //자기자신
            return node;
        }else if(node == p || node == q){
            // p와 q가 부모 자식인 경우, 여러번 조회하는 경우를 방지함
            return node;
        }else{
            return x == null ? y : x;
        }
    }

    //방법 5. 찾았는지 못찾았는지 객체로 저장하여 해당 포인터를 반환함
    class Result{ //결과를 저장할 클래스를 선언
        Node node;
        boolean isAncestor;
        Result(Node node, boolean isAncestor){
            this.node = node;
            this.isAncestor = isAncestor;
        }
    }

    Node commonAncestor5(int d1, int d2){
        Node p = getNode(d1);
        Node q = getNode(d2);
        Result r= commonAncestor5(root,p,q);
        if (r.isAncestor){
            return r.node;
        }
        return null;
    }

    Result commonAncestor5(Node node, Node p, Node q){
        if (node == null) return new Result(null,false);
        if (node == p && node == q) return new Result(node,true);
        Result x = commonAncestor5(node.left,p,q); // 왼
        if (x.isAncestor){
            return x;
        }
        Result y = commonAncestor5(node.right,p,q); //오른
        if (y.isAncestor){
            return y;
        }
        if (x.node != null && y.node !=null){ //자기자신
            return new Result(node,true);
        }else if(node == p || node == q){
            boolean isAncestor = x.node != null || y.node != null;
            return new Result(node,isAncestor);
        }else{
            return new Result(x.node != null ? x.node : y.node, false);
        }
    }

}

public class TreeApply5 {
    public static void main(String[] args) {
        Tree1 tree = new Tree1(10);
        System.out.println(tree.commonAncestor(5,9).data);
        System.out.println(tree.commonAncestor2(5,9).data);
        System.out.println(tree.commonAncestor3(5,9).data);
        System.out.println(tree.commonAncestor4(5,9).data);
        System.out.println(tree.commonAncestor5(5,9).data);
    }
}
