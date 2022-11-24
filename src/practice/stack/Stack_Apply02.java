package practice.stack;

import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.util.EmptyStackException;
import java.util.Queue;
import java.util.Stack;

/**
 * 응용 1. 2개의 Stack으로 Queue 생성
 * 응용 2. Stack 정렬 (Stack 2개까지 가능, 다른 자료구조는 불가)
 */

// 응용 1
class StackQueue<T>{
    Stack<T> stackIn;
    Stack<T> stackOut;

    StackQueue(){
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    int size(){return stackIn.size() + stackOut.size();};

    boolean isEmpty(){return this.size() == 0;}

    void shiftOld(){
        if (stackOut.isEmpty()) while(!stackIn.isEmpty()) stackOut.push(stackIn.pop());
    }

    void add(T data){stackIn.push(data);}

    T remove(){
        if (isEmpty()) throw new EmptyStackException();
        shiftOld();
        return stackOut.pop();
    }

    T peek(){
        if (isEmpty()) throw new EmptyStackException();
        shiftOld();
        return stackOut.peek();
    }

}

// 응용 2
class StackSort{
    Stack<Integer> stack;
    Stack<Integer> stackSort;

    StackSort(){
        stack = new Stack<>();
        stackSort = new Stack<>();
    }

    void push(int data){stack.push(data);}

    // 응용 2.1 loop문
    void sortLoop(){
        while(!stack.isEmpty()){
            int data = stack.pop();
            while(!stackSort.isEmpty() && stackSort.peek() > data){
                stack.push(stackSort.pop());
            }
            stackSort.push(data);
        }
    }

    // 응용 2.2 재귀호출
    void sortRec(){
        if (stack.isEmpty()) return;
        int data = stack.pop();
        if (!stackSort.isEmpty() && stackSort.peek() > data){
            shiftSort(data);
        }
        stackSort.push(data);
        sortRec();
    }

    void shiftSort(int data){
        if (stackSort.isEmpty() || stackSort.peek() < data) return;
        stack.push(stackSort.pop());
        shiftSort(data);
    }


    public void stackInfo() throws EmptyStackException{
        if(stackSort.isEmpty()){
            throw new EmptyStackException();
        }
        while (!stackSort.isEmpty()){
            System.out.print(stackSort.pop()+"  ");
        }
        System.out.println();
    }
}


public class Stack_Apply02 {
    public static void main(String[] args) {
        StackQueue<Integer> queue = new StackQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println(queue.peek());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

        queue.add(6);
        System.out.println(queue.remove());

        StackSort stack = new StackSort();
        stack.push(4);
        stack.push(6);
        stack.push(1);
        stack.push(9);
        stack.push(7);

//        stack.sortLoop();
        stack.sortRec();
        stack.stackInfo();
    }
}
