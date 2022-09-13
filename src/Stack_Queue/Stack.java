package Stack_Queue;

import java.util.EmptyStackException;
/**
 * Stack : LIFO(후입선출 방식)
 */
class StackTest<T>{

    class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data){
            this.data = data;
        }
    }

    //Stack 후입선출 방식이기 때문에, top을 지칭하는 변수가 필요하다.
    //변수가 1개만 필요한 이유는 in / out 모두 top에서만 일어나기 때문이다.
    private Node<T> top;

    // pop() : 데이터 삭제
    public T pop(){
        if(top == null){
            throw new EmptyStackException();
        }
        T item = top.data;
        top = top.next;
        return item;
    }

    // push() : 데이터 삽입
    public void push(T item){
        Node<T> t = new Node<T>(item);
        t.next = top;
        top = t;
    }

    // peek() : 데이터 호출(마지막에 넣은 데이터)
    public T peek(){
        if(top == null){
            throw new EmptyStackException();
        }
        return  top.data;
    }

    // isEmpty() : 데이터 유무 확인
    public boolean isEmpty(){
        return top == null;
    }

}

public class Stack {
    public static void main(String[] args){
        StackTest<Integer> s = new StackTest<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.peek());
        System.out.println(s.pop());
        System.out.println(s.isEmpty());
        System.out.println(s.pop());
        System.out.println(s.isEmpty());
    }
}
