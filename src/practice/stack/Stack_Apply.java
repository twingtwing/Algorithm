package practice.stack;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 응용 1. Stack 가장 작은 값을 반환하는 함수
 *         단, 0[1] 의 시간만 걸려야한다. => 그러기위해서는 추가공간을 사용해야한다.
 * 응용 2. 여러개의 stack이 하나의 stack으로 동작
 */

// 응용 1

// 경우 1. Node에 여유공간을 만든 경우
class NodeWithMin{
    int data;
    int min;
    NodeWithMin(int data, int min){
        this.data = data;
        this.min = min;
    }
}

class StackWithMin extends Stack<NodeWithMin> {
    void push(int data){
        int min = Math.min(data, min());
        super.push(new NodeWithMin(data,min));
    }

    int min(){
        if(this.isEmpty()) return Integer.MAX_VALUE;
        else return this.peek().min;
    }
}

// 경우 2. Stack을 따로 만든 경우
class StackWithMin2 extends Stack<Integer>{
    Stack<Integer> sMin;

    StackWithMin2(){sMin = new Stack<>();}

    public void push(int data){
        if(data < min()) this.sMin.push(data);
        super.push(data);
    }

    public Integer pop(){
        int result = super.pop();
        if (result <= min()) this.sMin.pop();
        return result;
    }

    public int min(){
        if (this.isEmpty()) return Integer.MAX_VALUE;
        else return this.sMin.peek();
    }
}

// 응용 2

class SetOfStacks{
    int capacity; //stack에 들어갈 수 있는 최대 용량
    ArrayList<Stacks<Integer>> stacks;

    SetOfStacks(int capacity){
        this.capacity = capacity;
        stacks = new ArrayList<>();
    }

    boolean isEmpty(){ return this.stacks.size() == 0;}

    Stacks<Integer> getLastStacks(){
        if (isEmpty()) return null;
        return this.stacks.get(this.stacks.size() - 1);
    }

    void addStacks(){this.stacks.add(new Stacks<>(capacity));}

    void removeStacks(){stacks.remove(this.stacks.size() - 1);}

    void push(int data){
        Stacks<Integer> last = getLastStacks();
        if (last == null || last.isFull()){
            addStacks();
            last = getLastStacks();
        }
        try {
            last.push(data);
        } catch (FullStackException e) {
            e.printStackTrace();
        }
    }

    int pop(){
        Stacks<Integer> last = getLastStacks();
        if (last == null || last.isEmpty()) throw new EmptyStackException();
        int result = last.pop();
        if (last.isEmpty()) removeStacks();
        return result;
    }

    int popAt(int stackIndex){
        if (isEmpty()) throw new EmptyStackException();
        if (stackIndex >= stacks.size()) throw new IndexOutOfBoundsException();
        Stacks<Integer> stackNow = this.stacks.get(stackIndex);
        if (stackNow == null || stackNow.isEmpty()) throw new EmptyStackException();
        int result = stackNow.pop();
        shiftLeft(stackIndex);
        return result;
    }

    void shiftLeft(int stackIndex) {
        if (stackIndex >= stacks.size()-1) return;
        Stacks<Integer> stackNow = this.stacks.get(stackIndex);
        Stacks<Integer> stackNext = this.stacks.get(stackIndex+1);
        try {
            stackNow.push(stackNext.popBottom().data);
        } catch (FullStackException e) {
            e.printStackTrace();
        }
        if (stackNext.isEmpty()) removeStacks();
        shiftLeft(stackIndex + 1);
    }

    void retrive(){
        if (isEmpty()) return;
        Stacks<Integer> stack = null;
        for(int i = 0; i < this.stacks.size(); i++){
            System.out.print((i+1)+"번째 stack : ");
            stack = this.stacks.get(i);
            stack.retrive();
        }
    }

}

class Stacks<E>{
    int size;
    int capacity;
    Node top;
    Node bottom;

    class Node{
        E data;
        Node prev;
        Node next;

        Node(){}
        Node(E data){this.data = data;}
    }

    Stacks(int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.top = new Node();
    }

    boolean isEmpty(){return this.top.next == null;}

    boolean isFull(){return this.size == this.capacity;}

    int getSize(){return this.size;}

    void push(E data) throws FullStackException {
        if (isFull()) throw new FullStackException();
        push(new Node(data));
    }

    void push(Node node) throws FullStackException {
        if (isFull()) throw new FullStackException();
        if (isEmpty()){
            this.bottom = node;
        }else{
            this.top.next.prev = node;
            node.next = this.top.next;
        }
        this.top.next = node;
        node.prev = this.top;
        this.size++;
    }

    E pop(){
        if (isEmpty()) throw new EmptyStackException();
        E data = this.top.next.data;
        this.top.next = this.top.next.next;
        if (isEmpty()) this.bottom = null;
        else this.top.next.prev = this.top;
        this.size--;
        return data;
    }

    E peek(){
        if (isEmpty()) throw new EmptyStackException();
        return this.top.next.data;
    }

    Node popBottom(){
        if (isEmpty()) throw new EmptyStackException();
        Node result = this.bottom;
        if (this.top.next == result){
            this.top.next = null;
            this.bottom = null;
        }else{
            this.bottom = this.bottom.prev;
            this.bottom.next = null;
        }
        this.size--;
        return result;
    }

    void retrive(){
        if (isEmpty()) return;
        Node node = this.top.next;
        while(node.next != null){
            System.out.print(node.data + " -> ");
            node = node.next;
        }
        System.out.print(node.data);
        System.out.println();
    }

}

public class Stack_Apply {
    public static void main(String[] args) {

        StackWithMin stack = new StackWithMin();
        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(2);
        stack.push(9);
        stack.push(4);
        System.out.println(stack.min());


        StackWithMin2 stack2 = new StackWithMin2();
        stack2.push(5);
        stack2.push(3);
        System.out.println(stack2.min());
        stack2.push(7);
        stack2.push(2);
        stack2.push(9);
        stack2.push(4);
        System.out.println(stack2.min());

        SetOfStacks sos = new SetOfStacks(3);
        sos.push(1);
        sos.push(2);
        sos.push(3);

        sos.push(4);
        sos.push(5);
        sos.push(6);

        sos.push(7);
        sos.push(8);

        sos.retrive();
        System.out.println(sos.pop());
        sos.retrive();
        System.out.println(sos.popAt(1));
        sos.retrive();
        System.out.println(sos.popAt(0));
        sos.retrive();
        System.out.println(sos.pop());
        sos.retrive();
    }
}
