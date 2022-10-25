package 엔지니어_대한민국.Stack_Queue;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 1. 웅용 : 2개의 Stack(후입선출)으로 Queue(선입선출) 생성
 * 2. 응용 : Stack 정렬 (stack 2개까지 가능, 다른 자료구조는 불가)
 */
class MyQueue<T>{
    Stack<T> stackNew, stackOld;

    MyQueue(){
        stackNew = new Stack<>();
        stackOld = new Stack<>();
    }

    public int size(){
        return stackOld.size() + stackNew.size();
    }

    public void add(T data){
        stackNew.push(data);
    }

    private void shiftStahck(){
        if (stackOld.isEmpty()){
            while(!stackNew.isEmpty()){
                stackOld.push(stackNew.pop());
            }
        }
    }

    public T remove(){
        shiftStahck();
        T data = stackOld.pop();
        return data;
    }

    public T peek(){
        shiftStahck();
        return stackOld.peek();
    }

}

class StackSort{
    Stack<Integer> stack;
    Stack<Integer> stackSort;

    StackSort(){
        stack = new Stack<>();
        stackSort = new Stack<>();
    }
    
    public void push(int data){
        stack.push(data);
    }

    //방법 1. loop뮨
    public void sortLoop(){
        while(!stack.isEmpty()){
            int tmp = stack.pop();
            while(!stackSort.isEmpty() && stackSort.peek() > tmp){
                stack.push(stackSort.pop());
            }
            stackSort.push(tmp);
        }
    }

    //방법 2. 재귀호출
    public void sort(){
        if(!stack.isEmpty()){
            int data = stack.pop();
            if (stackSort.isEmpty() || data > stackSort.peek()){
                stackSort.push(data);
            }else{
                shift(data);
            }
            sort();
        }
    }
    
    public void shift(int data){
        if(!stackSort.isEmpty() && data < stackSort.peek()){
            stack.push(stackSort.pop());
            shift(data);
        }else if(stackSort.isEmpty()|| data > stackSort.peek()){
            stackSort.push(data);
        }
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

public class StackApply3 {
    public static void main(String[] args){
//        MyQueue<Integer> queue = new MyQueue<>();
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        queue.add(4);
//        queue.add(5);
//
//        System.out.println(queue.peek());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//
//        queue.add(6);
//        System.out.println(queue.remove());

        StackSort queue2 = new StackSort();
        queue2.push(4);
        queue2.push(6);
        queue2.push(1);
        queue2.push(9);
        queue2.push(7);

//        queue2.sort();
        queue2.sortLoop();
        queue2.stackInfo();

    }
}
