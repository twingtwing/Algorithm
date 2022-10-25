package 자바로_배우는_쉬운_자료구조.LinkedList;

class Node{
    private String data;
    public Node link;
    public Node(){
        this.data = null;
        this.link = null;
    }

    public Node(String data){
        this.data = data;
        this.link = null;
    }

    public Node(String data, Node link){
        this.data = data;
        this.link = link;
    }

    public String getData(){
        return this.data;
    }
}

class LinkedList{
    private Node head;
    public LinkedList(){
        head = null;
    }
    
    //insert 연산
    public void insertMiddleNode(Node pre, String data){ 
        if (pre == null) return;
        Node node = new Node(data);
        node.link = pre.link;
        pre.link = node;
    }
    public void insertLastNode(String data){
        Node node = new Node(data);
        if (head == null){
            this.head = node;
        }else{
            Node temp = head; // 임시 참조 변수에 주소를 참조
            while (temp.link != null) temp = temp.link; // node 순회
            temp.link = node;
        }
    }
    
    //delete 연산
    public void deleteLastNode(){
        Node pre, temp;
        if (head == null) return;
        if (head.link == null){
            head = null;
        }else{
            pre = head;
            temp = head.link;
            while (temp.link != null){
                pre = temp;
                temp = temp.link;
            }
            pre.link = null;
        }
    }

    //Search
    public Node searchNode(String data){
        if (head ==null) return null;
        Node temp = head.link;
        while (temp != null){
            if (temp.getData() == data) return temp;
            temp = temp.link;
        }
        return null;
    }

    public void printList(){
        if (head == null) return;
        Node temp = head;
        while (temp != null){
            System.out.print(temp.getData());
            temp = temp.link;
        }
        System.out.println();
    }
    
}

public class SinglyLinkedList {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insertLastNode("월");
        list.insertLastNode("화");
        list.insertLastNode("수");
        list.printList();

        list.insertMiddleNode(list.searchNode("수"),"금");
        list.printList();

        list.deleteLastNode();
        list.printList();
    }
}