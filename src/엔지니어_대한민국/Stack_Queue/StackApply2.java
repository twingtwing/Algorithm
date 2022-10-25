package 엔지니어_대한민국.Stack_Queue;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * 1. 웅용 : 데이터를 저장하다가 어느 지점에 이르면 새로운 스택에 저장하는 식으로 Set Of Stackss를 구현
 *          내부적으로는 여러개 스택으로 나뉘지만, push와 pop은 여전히 하나의 stack인거 처럼 동작해야함
 *          추가적으로, 세트 중 하나의 Stacks을 지정해서 데이터를 꺼내올수 있은 popAt()함수를 만드시오
 */
class SetOfStacks{
    int capacity; // 얼마나 쌓였는지 확인하는 변수
    ArrayList<Stacks<Integer>> stacks = new ArrayList<Stacks<Integer>>();

    SetOfStacks(int capacity){
        this.capacity = capacity;
    }

    public Stacks<Integer> getLastStacks(){ //마지막 스택을 호출
        if(stacks.size() ==0 ) return null;
        return stacks.get(stacks.size()-1);
    }

    public void addStacks(){
        stacks.add(new Stacks<Integer>(capacity));
    }

    public void removeLastStacks(){//마지막 스택을 삭제
        stacks.remove(stacks.size()-1);
    }

    public void push(int data){
        Stacks<Integer> last = getLastStacks();
        if (last==null || last.size() == capacity){
            addStacks();
            last = getLastStacks();
        }
        try {
            last.push(data);
        } catch (FullStackException e) {
        }
    }

    public int pop(){
        Stacks<Integer> last = getLastStacks();
        if (last==null || last.isEmpty()){
            throw new EmptyStackException();
        }
        int data = last.pop();
        if(last.isEmpty()){
            removeLastStacks();
        }
        return data;
    }

    public int popAt(int index){
        if (stacks.size() == 0){
            throw new EmptyStackException();
        }
        if(index <0 || index > stacks.size()){
            throw new IndexOutOfBoundsException();
        }
        Stacks<Integer> stack = stacks.get(index);
        if (stack == null || stack.isEmpty()){
            throw new EmptyStackException();
        }
        int data = stack.pop();
        shfitLeft(index);
        return data;
    }

    public void shfitLeft(int index){
        if (index  < stacks.size() -1 ){
            Stacks<Integer> left = stacks.get(index);
            Stacks<Integer> right = stacks.get(index+1);
            try { left.push(right.popBottom()); } catch (FullStackException e) {}
            if(right.isEmpty()){
                stacks.remove(index+1);
            }
            shfitLeft(index+1);
        }
    }

}

class Stacks<E>{

    class Node{
        E data;
        Node above;
        Node below;
        Node (E data){
            this.data = data;
        }
    }

    int capacity;
    int size;
    Node top;
    Node bottom; //shift시에 맨처음에 삽입한 값을 알아야하기 때문


    Stacks(int capacity){
        this.capacity = capacity;
    }

    public boolean isEmpty(){return size==0;}

    public boolean isFull(){return size==capacity;}

    public int size(){return size;}

    public void push(E data) throws FullStackException{
        if (isFull()){
            throw new FullStackException();
        }
        Node n = new Node(data);
        push(n);
    }

    public void push(Node n) throws FullStackException{
        if (isFull()){
            throw new FullStackException();
        }
        if(isEmpty()){
            top = n;
            bottom = n;
        }else{
            top.above = n; //Node안의 위 아래값 변경
            n.below = top;
            top = n; // top값 변경
        }
        size ++;
    }

    public E pop() throws EmptyStackException{
        if (isEmpty()){
            throw new EmptyStackException();
        }
        E data = top.data;
        top = top.below;
        if (top == null){
            bottom = null;
        }else{
            top.above = null;
        }
        size --;
        return data;
    }

    public Node popBottom(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        Node n = bottom;
        bottom = bottom.above;
        if (bottom == null){
            top = null;
        }else{
            bottom.below = null;
        }
        size --;
        return n;
    }
}

public class StackApply2{
    public static void main(String[] args){
        SetOfStacks sos = new SetOfStacks(3);
        sos.push(1);
        sos.push(2);
        sos.push(3);

        sos.push(4);
        sos.push(5);
        sos.push(6);

        sos.push(7);
        sos.push(8);

        System.out.println(sos.pop());
        System.out.println(sos.popAt(0));
        System.out.println(sos.popAt(1));
        System.out.println(sos.pop());
    }
}
