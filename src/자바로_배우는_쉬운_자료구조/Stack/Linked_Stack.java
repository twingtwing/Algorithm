package 자바로_배우는_쉬운_자료구조.Stack;

class StackNode{
    char data;
    StackNode link;
}

class LinkedStack implements Stack{
    private StackNode top;

    LinkedStack(){
        this.top = null;
    }

    @Override
    public boolean isEmpty() {
        return (top == null);
    }

    @Override
    public void push(char item) {
        StackNode node = new StackNode();
        node.data = item;
        node.link = this.top;
        this.top = node;
    }

    @Override
    public char pop() {
        if (isEmpty()) return 0;
        char result = this.top.data;
        this.top = this.top.link;
        return result;
    }

    @Override
    public void delete() {
        if (isEmpty()) return;
        this.top = this.top.link;
    }

    @Override
    public char peek() {
        if (isEmpty()) return 0;
        return this.top.data;
    }

    public void printStack(){
        if (isEmpty()) return;
        StackNode temp = top;
        while (temp != null){
            System.out.print(temp.data+" ");
            temp = temp.link;
        }
        System.out.println();
    }
}

public class Linked_Stack {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();

        stack.push('A');
        stack.push('B');
        stack.push('C');
        stack.printStack();

        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.printStack();
    }
}
