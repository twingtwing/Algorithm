package Stack_Queue;

import java.util.EmptyStackException;
/**
 * Stack : LIFO(후입선출 방식) => Linked List 로 구현
 */
class StackTest<T>{

    //Stack 후입선출 방식이기 때문에, top을 지칭하는 변수가 필요하다.
    //변수가 1개만 필요한 이유는 in / out 모두 top에서만 일어나기 때문이다.
    private Node<T> top; // 단방향 Linked List에서 header와 같은 역활

    class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data){
            this.data = data;
        }
    }

    // pop() : 데이터 삭제
    public T pop(){
        if(top == null){
            throw new EmptyStackException();
        }
        T item = top.data; // 삭제할 대상의 data
        top = top.next; // 주소값을 next로 변경해서 top과의 연결을 끊어 버림
        return item;
    }

    // push() : 데이터 삽입
    public void push(T item){
        Node<T> t = new Node<T>(item);
        t.next = top; //새로 생성한 Node의 next값을 top으로 할당
        top = t; // top의 값이 바꼈기 때문에 변경
    }

    // peek() : 데이터 호출(마지막에 넣은 데이터)
    // 마지막에 넣은 데이터 = top -> 후입선출(LIFO)
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
