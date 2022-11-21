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
//              이때, 배열을 하나의 원으로 생각하여 여유공간을 빌려준다.
//              그러나 실제로는 일직선이므로 Mod를 사용하여 할당한다.
class StackFlowArray{

    Stack[] stacks;
    int[] stackAry;

    class Stack{
        int top;
        int len;
        int start;

        Stack(int len, int start){
            this.top = -1;
            this.len = len;
            this.start = start;
        }

        //입력한 index가 해당 stack영역안에 존재하는지 check
        boolean inStack(int index){
            if(index < 0 || index >= stackAry.length) return false;
            //배열을 하나로 원으로 생각하기 때문에 가상의 값을 만듦
            int fakeIndex = index < start ? index + stackAry.length : index;
            int fakeEnd = start + len;
            return start <= fakeIndex && fakeIndex < fakeEnd;
        }

        boolean isFull(){return this.top == this.len - 1;}

        boolean isEmpty(){return this.top == -1;}

        int getLastStackIndex(){return adjustIndex(start + len - 1);}

        int getLastDataIndex(){return adjustIndex(start + top);}

        int getNewDataIndex(){return adjustIndex(getLastDataIndex() + 1);}
    }

    StackFlowArray(int size, int len){
        stacks = new Stack[size];
        for (int i = 0; i < size; i++){
            stacks[i] = new Stack(len, len*i);
        }
        stackAry = new int[size * len];
    }

    // 가상의 index를 나머지값(%)을 통해 실제 Index값을 구함
    // index가 -일 경우,
    // %를 해도 -이므로 여기서 len값을 더해서 한 번더 %을 해준다.
    int adjustIndex(int index){
        int max = stackAry.length;
        return ((index % max) + max) % max;
    }

    int previousIndex(int index){return adjustIndex(index - 1);}

    int nextIndex(int index){return adjustIndex(index + 1);}

    boolean allStackFull(){return totalDataSize() == stackAry.length;}

    int totalDataSize(){
        int result = 0;
        for (Stack stack : stacks){
            result += stack.top + 1;
        }
        return result;
    }

    // 확장
    void expend(int stackIndex){
        int nextIndex = (stackIndex + 1) % stacks.length;
        if (stacks[nextIndex].isFull()) expend(nextIndex);
        shift(nextIndex);
        stacks[stackIndex].len ++;
    }

    // 뒤로 미르기
    void shift(int stackIndex){
        Stack stack = stacks[stackIndex];
        int index = stack.getLastStackIndex();
        while (stack.inStack(index)){ // 한칸씩 미뤄짐
            stackAry[index] = stackAry[previousIndex(index)];
            index = previousIndex(index);
        }
        stackAry[stack.start] = 0; //미뤄지기 전의 start는 초기화 해주어야함.
        stack.start = nextIndex(stack.start);
        stack.len--;
    }

    void push(int stackIndex, int data) throws FullStackException {
        if (allStackFull()) throw new FullStackException();
        Stack stack = stacks[stackIndex];
        if (stack.isFull()){
            expend(stackIndex);
        }
        stackAry[stack.getNewDataIndex()] = data;
        stack.top++;
    }

    int pop(int stackIndex){
        Stack stack = stacks[stackIndex];
        if (stack.isEmpty()) throw new EmptyStackException();
        int top = stack.getLastDataIndex();
        int result = stackAry[top];
        stackAry[top] = 0;
        stack.top--;
        return result;
    }

    int peek(int stackIndex){
        Stack stack = stacks[stackIndex];
        if (stack.isEmpty()) throw new EmptyStackException();
        return stackAry[stack.getLastDataIndex()];
    }

    void retireve(){
        for (Stack stack : stacks){
            if (stack.isEmpty()) continue;
            int index = stack.start;
            while (index != stack.getLastDataIndex()){
                System.out.print(stackAry[index] + " ");
                index = nextIndex(index);
            }
            System.out.println();

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

        System.out.println();

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

        System.out.println();

        StackFlowArray stackFlowArray = new StackFlowArray(3,5);
        try {
            for (int j = 0; j < 3; j++){
                for (int i = 0; i < 4; i++){
                    stackFlowArray.push(j,new Random().nextInt(10));
                }
            }

            stackFlowArray.push(0,new Random().nextInt(10));
            stackFlowArray.push(0,new Random().nextInt(10));
            stackFlowArray.push(0,new Random().nextInt(10));

        } catch (FullStackException e) {
            e.printStackTrace();
        }

        stackFlowArray.retireve();

        System.out.println(stackFlowArray.peek(0));
        System.out.println(stackFlowArray.pop(0));
        System.out.println(stackFlowArray.peek(1));
        System.out.println(stackFlowArray.pop(1));

        stackFlowArray.retireve();


    }
}
