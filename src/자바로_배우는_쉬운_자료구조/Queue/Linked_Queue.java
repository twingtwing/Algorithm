package 자바로_배우는_쉬운_자료구조.Queue;

class Node{
    char data;
    Node link;
}

class LinkedQueue implements Queue{
    private Node front;
    private Node rear;

    LinkedQueue(){
        this.front = null;
        this.rear = null;
    }

    @Override
    public boolean isEmpty() {
        return (this.front == null);
    }

    @Override
    public void enQueue(char item) {
        Node node = new Node();
        node.data = item;
        if (isEmpty()){
            this.front = node;
            this.rear = node;
        }else{
            rear.link = node;
            this.rear = node;
        }
    }

    @Override
    public char deQueue() {
        if (isEmpty()) return 0;
        char result = this.front.data;
        this.front = front.link;
        if(front == null) rear = null;
        return result;
    }

    @Override
    public void delete() {
        if (isEmpty()) return;
        this.front = front.link;
        if(front == null) rear = null;
    }

    @Override
    public char peek() {
        if (isEmpty()) return 0;
        return this.front.data;
    }

    public void printQueue(){
        if (isEmpty()) return;
        Node temp = this.front;
        while (temp != null){
            System.out.print(temp.data + " ");
            temp = temp.link;
        }
        System.out.println();
    }
}

public class Linked_Queue {
    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();

        queue.enQueue('A');
        queue.enQueue('B');

        System.out.println(queue.deQueue());

        queue.enQueue('C');

        queue.printQueue();

    }
}
