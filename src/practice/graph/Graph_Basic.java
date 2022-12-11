package practice.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Graph 구현
 * 1. 순차 자료구조 방식을 이용하여 Graph를 구현 : Adjacent Matrix
 * 2. 연결 자료구조 방식을 이용하여 Graph를 구현 : Adjacent List
 *
 * Graph 순회
 * 1. Depth - First - Search(DFS : 깊이 우선 방법)
 * 2. Breadth - First - Search(BFS : 넓이 우선 방법)
 */

// Adjacent List : List을 이용하여 인접한 node의 관계를 구현
class GraphList{

    Node [] nodes; // node들을 저장할 배열

    class Node {
        int data;
        boolean marked; // 방문 여부
        LinkedList<Node> adjacent; // 인접한 node
        Node(int data) {
            this.data = data;
            this.marked = false;
            adjacent = new LinkedList<>();
        }
    }

    GraphList(int size){
        this.nodes = new Node[size];
        for (int i = 0; i < size; i++) nodes[i] = new Node(i);
    }

    void addEdge(int i1, int i2){
        Node n1 = this.nodes[i1];
        Node n2 = this.nodes[i2];
        if (!n1.adjacent.contains(n2)) n1.adjacent.add(n2);
        if (!n2.adjacent.contains(n1)) n2.adjacent.add(n1);
    }

    // 1. DFS : 깊이 우선 방법 

    // 1.2 Stack으로 구현 => 후입 선출
    void searchStkDFS(){searchStkDFS(0);}

    private void searchStkDFS(int index) {searchStkDFS(this.nodes[index]);}

    private void searchStkDFS(Node node) {
        if (node == null) return;
        node.marked = true;

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while(!stack.isEmpty()){
            Node pop = stack.pop();

            for(Node n : pop.adjacent){
                if (!n.marked){
                    n.marked = true;
                    stack.push(n);
                }
            }

            System.out.print(pop.data + " ");
        }

    }

    // 1.1 재귀호출로 구현
    void searchRecDFS(){searchRecDFS(0);}

    private void searchRecDFS(int index) {searchRecDFS(this.nodes[index]);}

    private void searchRecDFS(Node node) {
        if (node == null || node.marked) return;
        node.marked = true;
        System.out.print(node.data + " ");
        for (Node n : node.adjacent) if (!n.marked) searchRecDFS(n);
    }

    // 2. BFS : 넓이 우선 방법 => Queue(선입선출)을 이용하여 구현
    void searchBFS(){searchBFS(0);}

    private void searchBFS(int index) {searchBFS(this.nodes[index]);}

    private void searchBFS(Node node) {
        if (node == null) return;
        node.marked = true;

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()){ // 형제 노드
            Node remove = queue.remove(); // 가장 처음에 넣은 node
            for (Node n : remove.adjacent)
                if (!n.marked){
                    n.marked = true;
                    queue.add(n); //형제 노드의 인접노드
                }
            System.out.print(remove.data + " ");
        }

    }

}

public class Graph_Basic {
    public static void main(String[] args) {
        GraphList graph = new GraphList(9);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,4);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(3,5);
        graph.addEdge(5,6);
        graph.addEdge(5,7);
        graph.addEdge(6,8);
        graph.searchStkDFS();
        System.out.println();
        System.out.println("======================");

        GraphList graph2 = new GraphList(9);
        graph2.addEdge(0,1);
        graph2.addEdge(1,2);
        graph2.addEdge(1,3);
        graph2.addEdge(2,4);
        graph2.addEdge(2,3);
        graph2.addEdge(3,4);
        graph2.addEdge(3,5);
        graph2.addEdge(5,6);
        graph2.addEdge(5,7);
        graph2.addEdge(6,8);
        graph2.searchRecDFS();
        System.out.println();
        System.out.println("======================");

        GraphList graph3 = new GraphList(9);
        graph3.addEdge(0,1);
        graph3.addEdge(1,2);
        graph3.addEdge(1,3);
        graph3.addEdge(2,4);
        graph3.addEdge(2,3);
        graph3.addEdge(3,4);
        graph3.addEdge(3,5);
        graph3.addEdge(5,6);
        graph3.addEdge(5,7);
        graph3.addEdge(6,8);
        graph3.searchBFS();
    }
}
