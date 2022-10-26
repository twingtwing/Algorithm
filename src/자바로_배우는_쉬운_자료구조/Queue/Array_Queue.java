package 자바로_배우는_쉬운_자료구조.Queue;

class ArrayQueue implements Queue{
    private int front;
    private int rear;
    private int queueSize;
    private char[] itemArray;

    ArrayQueue(){
        this.front = -1;
        this.rear = -1;
        this.queueSize = 0;
        this.itemArray = null;
    }
    ArrayQueue(int queueSize){
        this.front = -1;
        this.rear = -1;
        this.queueSize = queueSize;
        this.itemArray = new char[this.queueSize];
    }

    @Override
    public boolean isEmpty() {
        return (front == rear);
    }

    public boolean isFull(){
        return (rear == (queueSize -1));
    }

    @Override
    public void enQueue(char item) { // insert연산은 rear(가장 나중에 들어가는 데이터)
        if (isFull()) return;
        itemArray[++rear] = item;

    }

    @Override
    public char deQueue() { // delete연산은 front(가장 처음 들어가는 데이터)
        if (isEmpty()) return 0;
        return itemArray[++front];
    }

    @Override
    public void delete() {
        if (isEmpty()) return;
        ++front;
    }

    @Override
    public char peek() {
        return itemArray[front + 1];
    }

    public void printQueue(){
        if (isEmpty()) return;
        for (int i = front + 1; i <= rear; i++){
            System.out.print(itemArray[i] + " ");
        }
        System.out.println();
    }

}

public class Array_Queue {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);

        queue.enQueue('A');
        queue.enQueue('B');
        queue.enQueue('C');

        queue.printQueue();

        System.out.println(queue.deQueue());
        System.out.println(queue.peek());

        queue.printQueue();
    }
}
