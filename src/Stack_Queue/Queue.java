package Stack_Queue;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Queue : FIFO(선입선출 방식)
 */
class QueueTest<T>{

    //FIFO(선입선출 방식)이므로 앞뒤로 주소를 알고 있어야함
    private Node<T> first; //가장 먼저 넣은게 삭제되어야하고,
    private Node<T> last;  //가장 최근에 넣은게 끝으로 와야한다. 그렇기에 last는 next값이 존재하지 않음

    class Node<T>{
        private T data;
        private Node<T> next = null;

        public Node(T data){
            this.data = data;
        }
    }

    public void add(T item){
        Node<T> node = new Node<T>(item);
        if(last != null){//마지막 노드가 있다면,
            last.next = node;
        }
        last = node; //가장 최근에 넣은게 마지막에 와야한다.
        if (first == null){ //처음 노드도 값이 없다면,
            first = last;   //가장 처음에 넣은 노드가 first에 할당이 된다.
        }
    }

    public T remove(){
        if(first == null){
            throw new NoSuchElementException();
        }
        T item = first.data; //가장 처음에 넣은 노드가 삭제 되어야함
        first = first.next;
        if (first == null){ //첫번째 노드가 없어지면, 값이 모두 없어진 경우로 취급?
            last = null;
        }
        return item;
    }

    public T peek(){
        if(first == null){
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public boolean isEmpty(){
        return first ==  null; //first가null이면, last도 null임?
    }

}

public class Queue {
    public static void main(String[] args){
        QueueTest<Integer> q = new QueueTest<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.peek());
        System.out.println(q.remove());
        System.out.println(q.isEmpty());
        System.out.println(q.remove());
        System.out.println(q.isEmpty());
    }
}
