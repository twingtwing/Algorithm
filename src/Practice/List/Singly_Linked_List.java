package Practice.List;

class SinglyLinkedList{

    Node header;

    static class Node{
        int data;
        Node link;
    }

    SinglyLinkedList(){
        header = new Node();
    }

    void append(int data){
        Node end = new Node();
        end.data = data;
        Node next = this.header;
        while (next.link != null){
            next = next.link;
        }
        next.link = end;
    }

    void delete(int data){
        Node next = this.header;
        while (next.link != null){
            if (next.link.data == data)
                next.link = next.link.link;
            else
                next = next.link;
        }
    }

    void retrieve(){
        Node node = this.header.link;
        while (node != null){
            System.out.printf("%d ->",node.data);
            node = node.link;
        }
        System.out.println();
    }
}

public class Singly_Linked_List {
    public static void main(String[] args) {
        SinglyLinkedList ll = new SinglyLinkedList(); //헤드 노드 작성
        ll.append(1);
        ll.append(2);
        ll.append(3);
        ll.append(4);
        ll.retrieve();
        ll.delete(1);
        ll.retrieve();

    }
}
