package 자바로_배우는_쉬운_자료구조.Tree;

class Heap{
    private int heapSize;
    private int itempHeap[];

    public Heap(int size){
        heapSize = 0;
        itempHeap = new int[size];
    }

    public void insertHeap(int data){
        int i = ++heapSize;
        while ((i != 1) && (data > itempHeap[i/2])){
            itempHeap[i] = itempHeap[i/2];
            i /= 2;
        }
        itempHeap[i] = data;
    }

    public int getHeapSize(){
        return this.heapSize;
    }

    public int deleteHeap(){
        int parent, child;
        int data, temp;
        data = itempHeap[1]; // root : 인덱스 계산을 용이하기 위해 인덱스 0번을 비워두고, 인덱스 1번을 root로 한다.
        temp = itempHeap[heapSize--];
        parent = 1;
        child = 2;

        while (child<=heapSize){
            if ((child < heapSize) && (itempHeap[child] < itempHeap[child+1])) child++;
            if (temp >= itempHeap[child]) break;
            itempHeap[parent] = itempHeap[child];
            parent = child;
            child += 2;
        }
        itempHeap[parent] = temp;
        return data;
    }

    public void printHeap(){
        for (int i =1; i<=heapSize; i++){
            System.out.printf("[%d] ",itempHeap[i]);
        }
        System.out.println();
    }
}

public class Heap_Tree {
    public static void main(String[] args) {
        Heap heap = new Heap(50);

        heap.insertHeap(13);
        heap.insertHeap(8);
        heap.insertHeap(10);
        heap.insertHeap(15);
        heap.insertHeap(20);
        heap.insertHeap(19);

        heap.printHeap();

        System.out.println(heap.deleteHeap());
        heap.printHeap();

        System.out.println(heap.deleteHeap());
        heap.printHeap();

        System.out.println(heap.deleteHeap());
        heap.printHeap();

        System.out.println(heap.deleteHeap());
        heap.printHeap();

        System.out.println(heap.deleteHeap());
        heap.printHeap();

        System.out.println(heap.deleteHeap());
        heap.printHeap();

    }
}
