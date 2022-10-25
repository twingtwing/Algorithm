package 엔지니어_대한민국.Trees_Graphs;

import java.util.LinkedList;

/**
 * Graph 응용 : Graph에서 두개의 노드가 서로 찾아갈 수 있는 경로가 있는지 확인하는 함수를 구하시오
 *            => Graph Search의 bFS가 더 적합 why??
 *
 */
class Graphs{
    class Node{
        int data;
        LinkedList<Node> adj;
        boolean marked;
        Node (int data){
            this.data = data;
            this.marked = false;
            adj = new LinkedList<Node>();
        }
    }

    Node [] nodes;

    Graphs(int size){
        nodes = new Node[size];
        for(int i = 0; i<size; i++){
            nodes[i] = new Node(i);
        }
    }

    void addEdge(int i1, int i2){
        Node n1 = nodes[i1];
        Node n2 = nodes[i2];
        if (!n1.adj.contains(n2)){
            n1.adj.add(n2);
        }
        if (!n2.adj.contains(n1)){
            n2.adj.add(n1);
        }
    }
    
    void fullFalse(){
        for(Node n : nodes){
            n.marked = false;
        }
    }
    
    boolean bfsSearch(int start, int end){
        return bfsSearch(nodes[start],nodes[end]);
    }

    boolean bfsSearch(Node start, Node end){
        fullFalse();
        LinkedList<Node> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            Node root = q.removeFirst();
            if (root == end){
                return true;
            }
            for (Node n : root.adj){ //자식 노드들을 추가한다.
                if(n.marked == false){
                    n.marked = true;
                    q.add(n);
                }
            }
        }
        return false; // 경로를 찾지 못했다는 의미
    }

}

public class GraphApply {
    public static void main(String [] args) {
        Graphs g = new Graphs(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 8);

        System.out.println(g.bfsSearch(1,7));

        g.addEdge(3, 5);

        System.out.println(g.bfsSearch(1,7));
    }
}
