package practice.queue;

import java.util.NoSuchElementException;

/**
 * Queue : FIFO(선입선출 방식)
 */
class QueueLinkedList<T>{

    Node<T> front; //삭제연산이 일어나는 위치
    Node<T> rear;  //삽입연산이 일어나는 위치

    class Node<T>{
        T data;
        Node<T> link;
        Node(){}
        Node(T data){this.data = data;}
    }

    boolean isEmpty(){return this.front == null;}

    void enQueue(T data){
        Node<T> node = new Node<>(data);
        if (isEmpty()) this.front = node;
        if (this.rear != null) this.rear.link = node;
        this.rear = node;
    }

    T deQueue(){
        if(isEmpty()) throw new NoSuchElementException();
        T result = this.front.data;
        this.front = this.front.link;
        if (isEmpty()) this.rear = null;
        return result;
    }

    T peek(){
        if(isEmpty()) throw new NoSuchElementException();
        return this.front.data;
    }

    void retrive(){
        if (isEmpty()) throw new NoSuchElementException();
        Node<T> node =  this.front;
        while(node.link != null){
            System.out.print(node.data + "->");
            node = node.link;
        }
        System.out.print(node.data);
        System.out.println();
    }

}
public class Queue_Basic {
    public static void main(String[] args) {
        QueueLinkedList<Integer> queue = new QueueLinkedList<>();

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);

        queue.retrive();

        System.out.println(queue.deQueue());
        System.out.println(queue.peek());
        System.out.println(queue.deQueue());

        queue.retrive();

        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.isEmpty());



    }
}
