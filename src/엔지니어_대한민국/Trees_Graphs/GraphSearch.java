package 엔지니어_대한민국.Trees_Graphs;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Graph 검색 방법 :
 * 1. Depth - First - Search(DFS : 깊이 우선 방법)
 * 2. Breadth - First - Search(BFS : 넓이 우선 방법)
 */
class Queue<T>{
    private Node<T> first;
    private Node<T> last;

    class Node<T>{
        private T  data;
        private Node<T> next = null;

        public Node(T data){
            this.data = data;
        }
    }

    public void add(T item){
        Node<T> node = new Node<T>(item);
        if(last != null){
            last.next = node;
        }
        last = node;
        if (first == null){
            first = last;
        }
    }

    public T remove(){
        if(first == null){
            throw new NoSuchElementException();
        }
        T item = first.data;
        first = first.next;
        if (first == null){
            last = null;
        }
        return item;
    }

    public T peek(){
        if(first == null){
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public boolean isEmpty(){
        return first ==  null;
    }
}

class Graph{ // 그래프를 표현하는 방법 :  Adjacency List ?

    class Node{
        int data;
        LinkedList<Node> adjacent; // 인접한 노드들 간의 관계
        boolean marked; //방문 여부 확인
        Node (int data){
            this.data = data;
            this.marked = false;
            adjacent = new LinkedList<Node>();
        }
    }

    Node [] nodes; //노드들을 저장할 배열

    Graph(int size){
        nodes = new Node[size]; // 쉽게 하기 위해 그래프 갯수를 고정으로 함
        for (int i = 0; i<size; i++){
            nodes[i] = new Node(i);
        }
    }

    void addEdge(int i1, int i2){ // 두 노드간의 관계를 저장
        Node n1 = nodes[i1];
        Node n2 = nodes[i2];
        if(!n1.adjacent.contains(n2)){
            n1.adjacent.add(n2);
        }
        if(!n2.adjacent.contains(n1)){
            n2.adjacent.add(n1);
        }
    }

    // Depth - First - Search(DFS : 깊이 우선 방법) => Stack으로 구현
    void dfs(){
        dfs(0);
    }

    void dfs(int index){
        Node root = nodes[index];
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        root.marked = true;
        while(!stack.isEmpty()){
            Node r = stack.pop();
            for (Node node : r.adjacent){
                if(node.marked == false){
                    node.marked = true;
                    stack.push(node);
                }
            }
            visit(r);
        }
    }

    // Depth - First - Search(DFS : 깊이 우선 방법) => 재귀호출로 구현
    void dfsR(){
        dfsR(0);
    }

    void dfsR(int index){
        Node r = nodes[index];
        dfsR(r);
    }

    void dfsR(Node r){ //재귀호출을 할때는 linkedlist(adjacent)가 node를 주소를 가지고 있기 때문에 재귀함수는 노드를 받는 현태가 되어야함
        if (r == null) return;
        r.marked = true;
        visit(r);
        for (Node n : r.adjacent){
            if(n.marked == false){
                dfsR(n);
            }
        }
    }

    // Breadth - First - Search(BFS : 넓이 우선 방법) => Queue로 구현
    void bfs(){
        bfs(0);
    }

    void bfs(int index){
        Node root = nodes[index];
        Queue<Node> queue = new Queue<>();
        queue.add(root); //enqeue?
        root.marked = true;
        while (!queue.isEmpty()){
            Node r = queue.remove();
            for(Node node : r.adjacent){
                if(node.marked == false){
                    node.marked = true;
                    queue.add(node);
                }
            }
            visit(r);
        }
    }

    void visit(Node n){ //방문할때 출력해주는 함수
        System.out.print(n.data + " ");

    }

}

public class GraphSearch {
    public static void main(String [] args){
        Graph g = new Graph(9);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(2,4);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.addEdge(3,5);
        g.addEdge(5,6);
        g.addEdge(5,7);
        g.addEdge(6,8);
        g.dfs();
        System.out.println("======================");
        Graph g2 = new Graph(9);
        g2.addEdge(0,1);
        g2.addEdge(1,2);
        g2.addEdge(1,3);
        g2.addEdge(2,4);
        g2.addEdge(2,3);
        g2.addEdge(3,4);
        g2.addEdge(3,5);
        g2.addEdge(5,6);
        g2.addEdge(5,7);
        g2.addEdge(6,8);
        g2.dfsR();
        System.out.println("======================");
        Graph g3 = new Graph(9);
        g3.addEdge(0,1);
        g3.addEdge(1,2);
        g3.addEdge(1,3);
        g3.addEdge(2,4);
        g3.addEdge(2,3);
        g3.addEdge(3,4);
        g3.addEdge(3,5);
        g3.addEdge(5,6);
        g3.addEdge(5,7);
        g3.addEdge(6,8);
        g3.bfs();
    }
}
