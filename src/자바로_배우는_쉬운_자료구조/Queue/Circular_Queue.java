package 자바로_배우는_쉬운_자료구조.Queue;

class CircularQueue implements Queue{
    private int front;
    private int rear;
    private int queueSize;
    private char itemArray[];

    CircularQueue(int queueSize){
        this.front = 0;
        this.rear = 0;
        this.queueSize = queueSize;
        this.itemArray = new char[this.queueSize];
    }

    @Override
    public boolean isEmpty() {
        return (front == rear);
    }

    public boolean isFull(){ // 다음 index 값이 front일 경우
        // 공백 상태와 포화 상태를 구분하기 위해 자리를 1개 비워두기 때문에, 모든칸을 전부 사용하지 않는다.
        return ((this.rear + 1) % this.queueSize == front);
    }

    @Override
    public void enQueue(char item) {
        if (isFull()) return;
        rear = (rear + 1) % this.queueSize;
        itemArray[rear] = item;
        System.out.println(rear+" " +item);
    }

    @Override
    public char deQueue() { // front가 다음 인덱스로 수정
        if (isEmpty()) return 0;
        front = (front+1) % this.queueSize;
        return itemArray[front];
    }

    @Override
    public void delete() {
        if (isEmpty()) return;
        front = (front+1) % this.queueSize;
    }

    @Override
    public char peek() {
        if (isEmpty()) return 0;
        return itemArray[(front+1) % this.queueSize];
    }

    public void printQueue(){
        if (isEmpty()) return;
        int tempFront = this.front;
        int tempRear = this.rear;
        while (tempFront != tempRear){
            tempFront = (tempFront + 1) % this.queueSize;
            System.out.print(itemArray[tempFront]+" ");
        }
        System.out.println();
    }
}

public class Circular_Queue {
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(4);

        queue.enQueue('A');
        queue.enQueue('B');

        System.out.println(queue.deQueue());
        queue.printQueue();

        queue.enQueue('C');
        queue.enQueue('D');
        queue.enQueue('E'); 

        queue.printQueue();

    }
}
