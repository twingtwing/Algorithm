package 자바로_배우는_쉬운_자료구조.Graph;

class AdjMatrix{
    private int matrix[][] = new int[10][10];
    private int totalV = 0;

    public void insertVertex(){
        if (totalV < 10){
            totalV++;
        }
    }

    public void insertEdge(int val01, int val02){
        if (val01 < totalV && val02 < totalV){
            matrix[val01][val02] = 1;
        }
    }

    public void printMatrix(){
        for (int i = 0; i < totalV; i++){
            for (int j = 0; j<totalV; j++){
                System.out.printf("%2d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}

public class Array_Graph {
    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix();
        for (int i = 0; i<4; i++) adjMatrix.insertVertex();
        adjMatrix.insertEdge(0,3);
        adjMatrix.insertEdge(0,1);
        adjMatrix.insertEdge(1,3);
        adjMatrix.insertEdge(1,2);
        adjMatrix.insertEdge(1,0);
        adjMatrix.insertEdge(2,3);
        adjMatrix.insertEdge(2,1);
        adjMatrix.insertEdge(3,2);
        adjMatrix.insertEdge(3,1);
        adjMatrix.insertEdge(3,0);
        adjMatrix.printMatrix();
    }
}
