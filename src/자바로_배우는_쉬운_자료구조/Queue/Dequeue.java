package 자바로_배우는_쉬운_자료구조.Queue;

class DNode{
    char data;
    DNode rlink;
    DNode llink;
}

class Deque{
    DNode front;
    DNode rear;

    public Deque(){
        front = null;
        rear = null;
    }

    public boolean isEmpty(){
        return (front == null);
    }

    public void insertFront(char item){
        DNode node = new DNode();
        node.data = item;
        if (isEmpty()){
            front = node;
            rear = node;
        }else{
            front.llink = node;
            node.rlink = front;
            front = node;
        }
    }

    public void insertRear(char item){
        DNode node = new DNode();
        node.data = item;
        if (isEmpty()){
            front = node;
            rear = node;
        }else{
            rear.rlink = node;
            node.llink = rear;
            rear = node;
        }
    }

    public char deleteFront(){
        if (isEmpty()) return 0;
        char result = front.data;
        if (front.rlink == null){
            front = null;
            rear = null;
        }else{
            front = front.rlink;
            front.llink = null;
        }
        return result;
    }

    public char deleteRear(){
        if (isEmpty()) return 0;
        char result = rear.data;
        if (rear.llink == null){
            front = null;
            rear = null;
        }else{
            rear = rear.llink;
            rear.rlink = null;
        }
        return result;
    }

    public void removeFront(){
        if (isEmpty()) return;
        if (front.rlink == null){
            front = null;
            rear = null;
        }else{
            front = front.rlink;
            front.llink = null;
        }
    }

    public void removeRear(){
        if (isEmpty()) return;
        if (rear.llink == null){
            front = null;
            rear = null;
        }else{
            rear = rear.llink;
            rear.rlink = null;
        }
    }

    public char peekFront(){
        if (isEmpty()) return 0;
        return front.data;
    }

    public char peekRear(){
        if (isEmpty()) return 0;
        return rear.data;
    }

    public void printDeque(){
        if (isEmpty()) return;
        DNode temp = this.front;
        while (temp != null){
            System.out.print(temp.data +" ");
            temp = temp.rlink;
        }
        System.out.println();
    }

}

public class Dequeue {
    public static void main(String[] args) {
        Deque deque = new Deque();

        deque.insertFront('A');
        deque.insertFront('B');
        deque.insertRear('C');

        deque.printDeque();

        System.out.println(deque.deleteFront());
        System.out.println(deque.deleteRear());

        deque.insertRear('D');
        deque.insertFront('E');
        deque.insertFront('F');

        deque.printDeque();
    }
}
