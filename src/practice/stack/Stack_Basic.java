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
        return partIndex * len + this.top[partIndex];
    }

    void push(int partIndex, int data) throws FullStackException{
        if (isFull(partIndex)) throw new FullStackException();
        this.stackAry[getIndex(partIndex) + 1] = data;
        this.top[partIndex]++;
    }

    int pop(int partIndex){
        if (isEmpty(partIndex)) throw new EmptyStackException();
        int result = this.stackAry[getIndex(partIndex)];
        this.top[partIndex]--;
        return result;
    }

    int peek(int partIndex){
        if (isEmpty(partIndex)) throw new EmptyStackException();
        return this.stackAry[getIndex(partIndex)];
    }

    void retireve(){
        for (int i = 0; i < this.part; i++){
            if (isEmpty(i)) throw new EmptyStackException();
            System.out.print(i+1+" 번째 stack : ");
            int limit = this.top[i] + i*this.len;
            for (int j = 0; j < this.len; j++){
                int index = i * this.len + j;
                if (limit >= index) {
                    System.out.print(stackAry[index]+" ");
                }
            }
            System.out.println();
        }
    }

}

// 2.2 유동길이 : 배열을 이용하여 여러개의 Stack을 구현한다.
//              stack이 공간을 모두 사용하였을 경우에는 다른 stack의 공간을 사용한다.
//              이때, 배열을 하나의 원으로 생각하여 공간을 비려준다. 그러나 실제로는 일직선이므로 Mod를 사용하여 할당한다.
class StackFlowArray{

    class Stack{
        int top;
        int len;
        int start;

        Stack(int len, int start){
            this.top = -1;
            this.len = len;
            this.start = start;
        }



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

        StackFixArray stackFixArray = new StackFixArray(5,3);
        try {
            for (int j = 0; j < 3; j++){
                for (int i = 0; i < 5; i++){
                    stackFixArray.push(j,new Random().nextInt(10));
                }
            }
        } catch (FullStackException e) {
            e.printStackTrace();
        }

        stackFixArray.retireve();

        System.out.println(stackFixArray.pop(0));
        System.out.println(stackFixArray.pop(0));
        System.out.println(stackFixArray.peek(0));
        System.out.println(stackFixArray.pop(0));
        System.out.println(stackFixArray.isEmpty(0));
        System.out.println(stackFixArray.pop(0));
        System.out.println(stackFixArray.pop(0));
        System.out.println(stackFixArray.isEmpty(0));

        try {
            stackFixArray.push(0,new Random().nextInt(10));
        } catch (FullStackException e) {
            e.printStackTrace();
        }

        System.out.println(stackFixArray.pop(2));

        stackFixArray.retireve();

    }
}
