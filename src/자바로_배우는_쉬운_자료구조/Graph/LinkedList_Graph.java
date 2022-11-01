package 자바로_배우는_쉬운_자료구조.Graph;

class GraphNode{
    int vertex;
    GraphNode link;
}

class AdjList{
    private GraphNode head[] = new GraphNode[10];
    private int totalV = 0;

    public void inserVertex(){
        totalV++;
    }

    public void insertEdge(int val01, int val02){
        if (val01 < totalV && val02 < totalV){
            GraphNode node = new GraphNode();
            node.vertex = val02;
            node.link = head[val01];
            head[val01] = node;
        }
    }
    
    public void printMatrix(){
        GraphNode node = new GraphNode();
        for (int i =0; i<totalV; i++){
            node = head[i];
            while (node != null){
                System.out.printf("-> %c",node.vertex + 65);
                node = node.link;
            }
            System.out.println();
        }
    }
}

public class LinkedList_Graph {
    public static void main(String[] args) {
        AdjList adjList = new AdjList();
        for (int i = 0; i<4; i++) adjList.inserVertex();
        adjList.insertEdge(0,3);
        adjList.insertEdge(0,1);
        adjList.insertEdge(1,3);
        adjList.insertEdge(1,2);
        adjList.insertEdge(1,0);
        adjList.insertEdge(2,3);
        adjList.insertEdge(2,1);
        adjList.insertEdge(3,2);
        adjList.insertEdge(3,1);
        adjList.insertEdge(3,0);
        adjList.printMatrix();
    }
}
