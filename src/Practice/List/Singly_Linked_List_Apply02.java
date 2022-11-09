package Practice.List;

import java.util.Random;

/*
 *  응용 1. 어떤 숫자를 자리수 별로 한개 씩 Linked List에 담고, 일의 자리가 헤더에 오도록 거꾸로 담았다.(ex, 346 : 6 -> 4-> 3)
 *          이런식의 Linked List 두 개를 받아서 합산하고, 같은 식으로 Linked List에 담아서 반환하라
 *  응용 2. 응용1에서 거꾸로가 아닌 경우
 */
class SinglyLinkedListApply2{

    Node header;

    class Node{
        int data;
        Node link;

        Node(){
        }

        Node(int data){
            this.data = data;
        }
    }

    SinglyLinkedListApply2(){
        header = new Node();
    }

    Node getFirst(){
        return this.header.link;
    }

    void append(int data){
        Node next = this.header;
        Node node = new Node(data);

        while (next.link != null){
            next = next.link;
        }
        next.link = node;
    }

    void delete(int data){
        Node node = this.header;
        while (node != null){
            if (node.link.data == data)
                node.link = node.link.link;
            else
                node = node.link;
        }
    }

    void retrieve(){
        Node n = header.link;
        while(n.link != null){
            System.out.print(n.data + "->");
            n = n.link;
        }
        System.out.println(n.data);
    }

    void retrieveNum(){
        Node n = header.link;
        String num = "";
        while(n.link != null){
            num = n.data +num;
            n = n.link;
        }
        num = n.data +num;
        System.out.println(num);
    }

    // 응용 1.1 loop
    void appendNum(int data){
        Node next = this.header;
        int num = data;
        while (num > 0){
            Node node = new Node(num%10);
            next.link = node;
            next = next.link;
            num = num / 10;
        }
    }

    void sumList(Node first, Node second){
        Node next = this.header;
        int sum = 0;
        while (first != null || second != null || sum > 0){
            int one = 0;
            int two = 0;
            if (first != null){
                one = first.data;
                first = first.link;
            }
            if (second != null){
                two = second.data;
                second = second.link;
            }
            sum = sum + one + two;
            Node node = new Node(sum%10);
            next.link = node;
            next = next.link;
            sum/=10;
        }
    }

}

public class Singly_Linked_List_Apply02 {
    public static void main(String[] args) {
        int num01 = new Random().nextInt(1000000);
        int num02 = new Random().nextInt(1000000);
        System.out.println(num01);
        System.out.println(num02);

        SinglyLinkedListApply2 list01 = new SinglyLinkedListApply2();
        list01.appendNum(num01);
        list01.retrieve();
        list01.retrieveNum();

        SinglyLinkedListApply2 list02 = new SinglyLinkedListApply2();
        list02.appendNum(num02);
        list02.retrieve();
        list02.retrieveNum();

        SinglyLinkedListApply2 list03 = new SinglyLinkedListApply2();
        list03.sumList(list01.getFirst(),list02.getFirst());
        list03.retrieve();
        list03.retrieveNum();

    }
}
