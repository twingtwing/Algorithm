package Practice.List;

import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;

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

    void retrieveNumRev(){
        Node n = header.link;
        String num = "";
        while(n.link != null){
            num = num + n.data;
            n = n.link;
        }
        num = num + n.data;
        System.out.println(num);
    }

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

    // 응용 1.1 loop
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

    // 응용 1.2 재귀호출
    void sumListRec(Node first, Node second){
        this.header.link = sumListRec(first,second,0);
    }

    Node sumListRec(Node first, Node second,int up){
        if (first == null && second == null && up == 0) return null;
        int one = 0, two = 0;
        if (first != null){
            one = first.data;
            first = first.link;
        }
        if (second != null){
            two = second.data;
            second = second.link;
        }
        int sum = one + two + up;
        Node result = new Node(sum%10);
        Node node = sumListRec(first,second,sum >= 10 ? 1 : 0);
        if (node != null){
            result.link = node;
        }
        return result;
    }

    // 응용 2.1 재귀호출 + 저장소
    class Storage{
        int up;
        Node node = null;
    }

    void sumRevRec(Node first, Node second){
        this.header.link = sumRevRec(first,second, new Storage());
    }

    Node sumRevRec(Node first, Node second, Storage storage){
        if (first == null && second == null && storage.up == 0) return null;
        int one = 0, two = 0;
        if (first != null){
            one = first.data;
            first = first.link;
        }
        if (second != null){
            two = second.data;
            second = second.link;
        }
        int sum = one + two + storage.up;
        Node result = new Node(sum%10);
        storage.up = sum >= 10 ? 1 : 0;

        Node node = sumRevRec(first,second,storage);
        if (storage.node != null){
            storage.node.link = result;
            storage.node = result;
            return node;
        }else{
            storage.node = result;
            return result;
        }
    }

    // 응용 2.1.2
    Node sumRevRec2(Node first, Node second){
        if (first == null && second == null) return null;
        int lenFirst = getListLength(first);
        int lenSecond = getListLength(second);

        // 두 list 길이를 맞춤
        if (lenFirst < lenSecond){
            first = LPadList(first,lenSecond - lenFirst);
        }else{
            second = LPadList(second,lenFirst - lenSecond);
        }

        Storage storage = addLists(first,second);
        if (storage.up != 0){
            storage.node = insertBefore(storage.node, storage.up);
        }
        return storage.node;
    }

    int getListLength(Node node){
        if (node== null) return 0;
        int count = 0;
        while (node != null){
            count ++;
            node = node.link;
        }
        return count;
    }

    Node LPadList(Node node, int length){
        if (length == 0) return node;
        Node result = node;
        while (length > 0){
            length--;
        }   node = insertBefore(node,0);
        return result;
    }

    Node insertBefore(Node node, int data){
        if (node == null) return null;
        node.link = new Node(data);
        return node;
    }

    Storage addLists(Node first, Node second){
        if (first == null && second == null) return null;
        int one = 0, two = 0;
        if (first != null){
            one = first.data;
            first = first.link;
        }
        if (second != null){
            two = second.data;
            second = second.link;
        }
        Storage storage = addLists(first, second);
        int num = one + two;
        if (storage != null){
            num = num + storage.up;
            storage.node = insertBefore(storage.node,num%10);
        }else{
            storage = new Storage();
            storage.node = new Node(num%10);
        }
        storage.up = num > 10 ? 1 : 0;
        return storage;
    }


    // 응용 2.2 합계를 구한 뒤에 새로운 LinkedList를 만듦
    void sumListTotal(Node first, Node second){
        if (first == null && second == null) return;
        int one = sumTotal(first);
        int two = sumTotal(second);
        this.header.link = madeListRev(one + two, (int)Math.log10(one + two));
    }

    int sumTotal(Node node){
        if (node == null) return 0;
        return sumTotal(node.link)*10 + node.data;
    }

    Node madeListRev(int data,int length){
        if (length == -1) return null;
        int num =  data/((int) Math.pow(10,length));
        Node node = new Node(num);
        node.link = madeListRev(data%((int) Math.pow(10,length)),--length);
        return node;
    }

    // 응용 2.2.2


}

public class Singly_Linked_List_Apply02 {
    public static void main(String[] args) {
        int num01 = new Random().nextInt(1000000);
        int num02 = new Random().nextInt(1000000);
        System.out.println(num01);
        System.out.println(num02);
        System.out.println(num01  + num02);

        SinglyLinkedListApply2 list01 = new SinglyLinkedListApply2();
        list01.appendNum(num01);
        list01.retrieve();
        list01.retrieveNum();

        SinglyLinkedListApply2 list02 = new SinglyLinkedListApply2();
        list02.appendNum(num02);
        list02.retrieve();
        list02.retrieveNum();

        // 응용 1.1 loop
        SinglyLinkedListApply2 list03 = new SinglyLinkedListApply2();
        list03.sumList(list01.getFirst(),list02.getFirst());
        list03.retrieve();
        list03.retrieveNum();

        // 응용 1.2 재귀호출
        SinglyLinkedListApply2 list05 = new SinglyLinkedListApply2();
        list05.sumListRec(list01.getFirst(),list02.getFirst());
        list05.retrieve();
        list05.retrieveNum();

        System.out.println();

        // 응용 2.1 재귀호출 + 저장소
        SinglyLinkedListApply2 list06 = new SinglyLinkedListApply2();
        list06.sumRevRec(list01.getFirst(),list02.getFirst());
        list06.retrieve();
        list06.retrieveNumRev();

        // 응용 2.1.2
        SinglyLinkedListApply2 list07 = new SinglyLinkedListApply2();
        list07.sumRevRec2(list01.getFirst(),list02.getFirst());
        list07.retrieve();
        list07.retrieveNumRev();

        System.out.println();

        // 응용 2.2.2 재귀호출 + 저장소
        SinglyLinkedListApply2 list08 = new SinglyLinkedListApply2();
        list08.sumListTotal(list01.getFirst(),list02.getFirst());
        list08.retrieve();
        list08.retrieveNumRev();

    }
}
