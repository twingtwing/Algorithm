package practice.stack;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Random;

// 1. Linked List로 구현
class StackLinkedList<T>{

    Node<T> top;

    class Node<T>{
        T data;
        Node<T> link;

        Node(){}
        Node(T data){this.data = data;}
    }

    StackLinkedList(){
        top = new Node<T>();
    }

    boolean isEmpty(){return this.top.link == null;}

    void push(T data){
        Node<T> node = new Node<>(data);
        node.link = this.top.link;
        this.top.link = node;
    }

    T pop(){
        if (isEmpty()) throw new EmptyStackException();
        T data = this.top.link.data;
        this.top.link = this.top.link.link;
        return data;
    }

    T peek(){
        if (isEmpty()) throw new EmptyStackException();
        return this.top.link.data;
    }

    void retrieve(){
        if (isEmpty()) throw new EmptyStackException();
        Node<T> next = this.top.link;
        while (next.link != null){
            System.out.print(next.data + "->");
            next = next.link;
        }
        System.out.print(next.data);
        System.out.println();
    }

}

// 2. Linear List로 구현

// 2.1 고정길이 : 배열을 이용하여 여러개의 Stack을 구현한다.
class StackFixArray{
    int len;
    int part;
    int[] top;
    int[] stackAry;

    StackFixArray(int len, int part){
        this.len = len;
        this.part = part;
        this.top = new int[part];
        this.stackAry = new int[len*part];

        Arrays.fill(top,-1);
    }

    boolean isEmpty(int partIndex){return this.top[partIndex] == -1;}

    boolean isFull(int partIndex){return this.top[partIndex] == len-1;}

    int getIndex(int partIndex){
        if (partIndex >= part) throw new IndexOutOfBoundsException();
        return partIndex * len + this.top[partIndex] + 1;
    }

    void push(int partIndex, int data) throws FullStackException{
        if (isFull(partIndex)) throw new FullStackException();
        int index = partIndex * len + this.top[partIndex] + 1;
        this.stackAry[index] = data;

    }
}

class FullStackException extends Exception{
    public FullStackException(){super();}
    public FullStackException(String msg){super(msg);}
}

public class Stack_Basic {
    public static void main(String[] args) {
        StackLinkedList<Integer> stackList = new StackLinkedList<>();
        for (int i = 0; i < 7; i++){
            stackList.push(new Random().nextInt(10));
        }
        stackList.retrieve();
        System.out.println(stackList.peek());
        System.out.println(stackList.pop());
        stackList.retrieve();
    }
}
