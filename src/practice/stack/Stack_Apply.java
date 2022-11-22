package practice.stack;

import com.sun.org.apache.xpath.internal.objects.XNodeSet;

import java.util.ArrayList;
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

    boolean isEmpty(){return stacks.size() == 0;}

    Stacks<Integer> getLastStacks(){
        if (isEmpty()) return null;
        return this.stacks.get(this.stacks.size() - 1);
    }

    void addStacks(){this.stacks.add(new Stacks<>(capacity));}

    void removeStacks(){stacks.remove(this.stacks.size() - 1);}



}

class Stacks<E>{
    int capacity;
    Node top;

    class Node{
        E data;
        Node link;

        Node(){}
        Node(E data){this.data = data;}
    }

    Stacks(int capacity){
        this.capacity = capacity;
        this.top = new Node();
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

    }
}
