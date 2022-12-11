package practice.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 응용 : Graph에서 2개의 node가 서로 찾아갈 수 있는 경로가 있는지 확인하는 알고리즘
*        !! keypoint !!
*        경로 조사이기 때문에 순회로 구해야하기 때문에 BFS를 이용
 */
class Graph{

    Node [] nodes;

    class Node{
        int data;
        boolean marked;
        LinkedList<Node> adjacent;
        Node(int data){
            this.data = data;
            this.marked = false;
            this.adjacent = new LinkedList<>();
        }

    }

    Graph(int size){
        this.nodes = new Node[size];
        for(int i = 0; i < size; i++) this.nodes[i] = new Node(i);
    }

    void addEdge(int i1, int i2){
        Node n1 = this.nodes[i1];
        Node n2 = this.nodes[i2];
        if (!n1.adjacent.contains(n2)) n1.adjacent.add(n2);
        if (!n2.adjacent.contains(n1)) n2.adjacent.add(n1);
    }

    void fullFalse(){for (Node node : nodes) node.marked = false;}

    boolean searchBFS(int start, int end){return searchBFS(this.nodes[start], this.nodes[end]);}

    boolean searchBFS(Node start, Node end) {
        if (start == null || end == null) return false;
        if (start == end) return true;
        fullFalse();

        Queue<Node> queue = new LinkedList<>();
        queue.add(start); // 무방향 그래프이기 때문에 가능

        while(!queue.isEmpty()){
            Node remove = queue.remove();
            for (Node node : remove.adjacent){
                if (!node.marked){
                    if (node == end) return true;
                    node.marked = true;
                    queue.add(node);
                }
            }

        }
        return false;
    }

}

public class Graph_Apply {
    public static void main(String[] args) {
        Graph graph = new Graph(9);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 8);

        System.out.println(graph.searchBFS(1,7));

        graph.addEdge(3, 5);

        System.out.println(graph.searchBFS(1,7));

    }
}
