package 엔지니어_대한민국.LinkedList;

import java.util.Random;
/*
 *  단방향 Linked List ( SinglyLinkedList ) 응용
 *  응용 1. 어떤 숫자를 자리수 별로 한개 씩 Linked List에 담고, 일의 자리가 헤더에 오도로 거꾸로 담았다.(ex, 346 : 6 -> 4-> 3)
 *          이런식의 Linked List 두 개를 받아서 합산하고, 같은 식으로 Linked List에 담아서 반환하라
 *  응용 2. 응용1에서 거꾸로가 아닌 경우
 */
public class SinglyLinkedListApply3{

    Node header;

    static class Node{
        int data;
        Node next = null;

        Node(){}

        Node(int data){
            this.data = data;
        }

    }

    SinglyLinkedListApply3(){
        header = new Node();
    }

    void append(int num){
        Node n = header;
        Node end = new Node();
        end.data = num;
        while(n.next != null){
            n = n.next;
        }
        n.next = end;
    }
    void delete(int data){
        Node n = header;
        while(n.next != null){
            if(n.next.data == data){
                n.next = n.next.next;
            }
            else{
                n = n.next;
            }
        }
    }
    void retrieve(){
        Node n = header.next;
        while(n.next != null){
            System.out.print(n.data + "->");
            n = n.next;
        }
        System.out.println(n.data);
    }
    static void retrieveNode(Node n){
        if(n==null){
            return;
        }
        while(n.next != null){
            System.out.print(n.data + "->");
            n = n.next;
        }
        System.out.println(n.data);
    }

    Node getFirst(){ return header.next; }

    // 응용1. 재귀 호출로 풀이
    private static Node sumeLists(Node l1, Node l2, int carry){
        if(l1 == null && l2 == null && carry == 0){
            return null; // 재귀호출 end 지점
        }
        Node result = new Node();
        int value = carry;
        if(l1 != null){
            value += l1.data;
        }
        if(l2 != null){
            value += l2.data;
        }
        result.data = value % 10;
        Node next = sumeLists(l1 == null? null:l1.next,
                              l2 == null? null:l2.next,
                              value >= 10? 1:0);
        result.next = next;
        return result;

    }

    // 응용2. 반대가 아닌 경우 재귀 호출로 풀이
    // 2.1 길이를 구해서 부족한 자릿수를 채워주고, 재귀호출
    // 노드를 list로 감싼 class 저장소를 만들어서 재귀호출을 통해 값을 구함
    private static class Storage{
        int carry = 0; //십의 자리수 저장
        Node result = null;
    }

    private static Node sumeLists(Node l1, Node l2){
        if(l1 == null && l2 == null){
            return null;
        }
        int len1 = getListLength(l1);
        int len2 = getListLength(l2);

        if(len1<len2){
            l1 = LPadList(l1,len2-len1);
        }else{
            l2 = LPadList(l2,len1-len2);
        }

        Storage storage = addLists(l1,l2);
        if(storage.carry !=0){
            storage.result = insertBefore(storage.result,storage.carry);
            return storage.result;
        }
        return storage.result;
    }

    private static Storage addLists(Node l1, Node l2){
        if(l1 == null && l2 == null){
            Storage storage = new Storage();
            return storage;
        }
        Storage storage = addLists(l1.next,l2.next);
        int value = storage.carry + l1.data + l2.data;
        int data = value % 10;
        storage.result = insertBefore(storage.result,data);
        storage.carry = value/10;
        return storage;
    }

    private static int getListLength(Node l){ // List 길이를 구함
        int total = 0;
        while(l != null){
            total++;
            l = l.next;
        }
        return total;
    }

    private static Node insertBefore(Node node,int data){ //노드를 앞에 연결해줌
        Node before = new Node(data);
        if(node != null){
            before.next = node;
        }
        return before;
    }

    private static Node LPadList(Node l, int length){ //length 만큼 앞에 0을 붙여줌
        Node head = l;
        for(int i=0; i<length;i++){
            head = insertBefore(head,0);
        }
        return head;
    }


    // 2.2 합계를 구한뒤에 새로운 Linked List를 만듦
    private static Node rev(Node l1, Node l2){
        if(l1 == null && l2 == null){
            return null;
        }
        int m1 = totalSum(l1);
        int m2 = totalSum(l2);
        int sum = m1+m2;
        return sumListsRev(sum,String.valueOf(sum).length());
    }

    private static int totalSum(Node n){
        int sum = 0;
        if (n == null){
            return 0;
        }
        while(n.next != null){
            sum = sum*10 + n.data;
            n = n.next;
        }
        sum = sum*10 + n.data;
        return sum;
    }

    private static Node sumListsRev(int num,int len){
        if(len == 0){
            return null;
        }
        Node result = new Node();
        int ten = (int)Math.pow(10,len-1);
        result.data = num >= ten ? num/ten : 0;
        int value = num >= ten ? num%ten : num;
        len--;
        result.next = sumListsRev(len==0?0:value,len);
        return result;
    }

    public static void main(String[] args){
        SinglyLinkedListApply3 ll1 = new SinglyLinkedListApply3();
        for(int i=1;i<10;i++){
            ll1.append(new Random().nextInt(10));
        }
        ll1.retrieve();

        SinglyLinkedListApply3 ll2 = new SinglyLinkedListApply3();
        for(int i=1;i<10;i++){
            ll2.append(new Random().nextInt(10));
        }
        ll2.retrieve();

        retrieveNode(sumeLists(ll1.getFirst(),ll2.getFirst(),0));
        System.out.println();

        long before = System.currentTimeMillis();
        retrieveNode(rev(ll1.getFirst(),ll2.getFirst()));
        long after = System.currentTimeMillis();
        System.out.println("실행시간(ms)"+(after-before));

        long before2 = System.currentTimeMillis();
        retrieveNode(sumeLists(ll1.getFirst(),ll2.getFirst()));
        long after2 = System.currentTimeMillis();
        System.out.println("실행시간(ms)"+(after2-before2));


    }
    
}

