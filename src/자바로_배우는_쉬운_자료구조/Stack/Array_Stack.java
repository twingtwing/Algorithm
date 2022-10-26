package 자바로_배우는_쉬운_자료구조.Stack;

class ArrayStack implements Stack{
    private int top; // top index를 저장하는 변수
    private int stackSize; 
    private char itemArray[];
    
    ArrayStack(){
        this.top = -1;
        this.stackSize = 0;
        this.itemArray = null;
    }
    ArrayStack(int stackSize){
        this.top = -1;
        this.stackSize = stackSize;
        this.itemArray = new char[stackSize];
    }

    @Override
    public boolean isEmpty() {
        return (this.top == -1);
    }
    
    public boolean isFull(){
        return (this.top == this.stackSize -1);
    }

    @Override
    public void push(char item) {
        if (isFull()) return;
        itemArray[++top] = item;
    }
    
    @Override
    public char pop() {
        if (isEmpty()) return 0;
        return itemArray[top--]; // top을 변경했기 때문에 값을 삭제하지 않음
    }

    @Override
    public void delete() {
        if (isEmpty()) return;
        top--;
    }

    @Override
    public char peek() {
        if (isEmpty()) return 0;
        return itemArray[top];
    }

    public void printStack(){
        if (isEmpty()) return;
        int temp = this.top;
        while (temp != -1){
            System.out.print(itemArray[temp--]+" ");
        }
        System.out.println();
    }
}

public class Array_Stack {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);

        stack.push('A');
        stack.push('B');
        stack.push('C');
        stack.printStack();

        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.printStack();
    }
}
