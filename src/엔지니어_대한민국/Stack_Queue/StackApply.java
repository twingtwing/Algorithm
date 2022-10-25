package 엔지니어_대한민국.Stack_Queue;

import java.util.EmptyStackException;
import java.util.Stack;

/**d
 * 1. 웅용 Stack 가장 작은 값을 반환하는 함수 구현 (단, 0[1] 의 시간만 걸려야한다.) => 한번 도는데도 0[n]의 시간이 걸린다.
 */
// 방법 1 : Node에 변수 공간을 한개 더 생성
class NodeWithMin{
    int value;
    int min;
    NodeWithMin(int v, int m){
        value = v;
        min = m;
    }
}

class StackWithMin extends Stack<NodeWithMin>{ //push만 재정의
    public int min(){
        if(this.isEmpty()){
            return Integer.MAX_VALUE;
        }else{
            return peek().min;
        }
    }

    public void push(int value){
        int newMin = Math.min(value,min());
        super.push(new NodeWithMin(value,newMin));
    }

}

// 방법 2 : min만의 Stack을 생성
class StackWithMin2 extends Stack<Integer>{ 
    Stack<Integer> min;
    
    public StackWithMin2(){
        min = new Stack<Integer>();
    }
    
    public int min(){
        if(this.isEmpty()){
            return Integer.MAX_VALUE;
        }else{
            return min.peek();
        }
    }

    public void push(int value){
        if(value < min()){
            min.push(value);
        }
        super.push(value);
    }
    
    public Integer pop(){
        int value = super.pop();
        if(value == min()){
            min.pop();
        }
        return value;
    }

}

// 방법 3
class StackA{

    private Node top;

    class Node{
        private int data;
        private int min; //Node마다 그 당시의 min값을 저장하면, pop()을 수행하면서, min값을 삭제해서 새로운 min값을 번거로움을 막을 수 있음
        private Node next = null;

        public Node(int data){
            this.data = data;
        }

    }
//  0[1]의 시간이 걸리는 알고리즘(매우 짧은 시간이 걸리려면, 공간을 사용해야함)
    public void push(int data){
        Node node = new Node(data);
        node.next = top;
        if (isEmpty() || top.min > node.data){
            node.min = node.data;
        }else{
            node.min = top.min;
        }
        top  = node;
    }

    public int pop() throws EmptyStackException{
        if (isEmpty()){
            throw new EmptyStackException();
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    public int min()throws EmptyStackException{
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return top.min;
    }


//  0[n]의 시간이 걸리는 알고리즘(한바퀴 돌기 때문)
//    public void push(int data){
//        Node node = new Node(data);
//        node.next = top;
//        top  = node;
//    }
//
//    public int pop() throws EmptyStackException{
//        if (isEmpty()){
//            throw new EmptyStackException();
//        }
//        int data = top.data;
//        top = top.next;
//        return data;
//    }
//
//    public int min(){
//        StackA test = new StackA();
//        test.top = top;
//        int min = test.top.data;
//        while(!test.isEmpty()){
//            int pop = test.pop();
//            if (min > pop ){
//                min = pop;
//            }
//        }
//        return min;
//    }
//
    private boolean isEmpty(){
        return top == null;
    }

}

public class StackApply {
    public static void main(String[] args){
        StackA stack = new StackA();
        stack.push(1);
        stack.push(3);
        stack.push(7);
        stack.push(2);
        stack.push(9);
        stack.push(4);
        System.out.println(stack.min());

        StackWithMin stack2 = new StackWithMin();
        stack2.push(5);
        stack2.push(3);
        stack2.push(7);
        stack2.push(2);
        stack2.push(9);
        stack2.push(4);
        System.out.println(stack2.min());


        StackWithMin2 stack3 = new StackWithMin2();
        stack3.push(5);
        stack3.push(3);
        System.out.println(stack3.min());
        stack3.push(7);
        stack3.push(2);
        stack3.push(9);
        stack3.push(4);
        System.out.println(stack3.min());        
    }
}
