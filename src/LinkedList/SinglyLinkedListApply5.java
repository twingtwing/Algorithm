package LinkedList;

import java.util.Random;

/*
 *  단방향 Linked List ( SinglyLinkedList ) 응용
 *  응용 1. 주어진 두개의 단방향 Linked List에서 교차노드를 찾는 알고리즘(단, 교차점은 주소로 찾아야함)
 *  응용 2. 루프가 있는 확인하고, 시작되는 지점을 Node를 찾는 알고리즘
 */
public class SinglyLinkedListApply5 {
    Node header;

    static class Node{
        int data;
        Node next = null;

        Node(){}

        Node(int data){
            this.data = data;
        }

        Node get(int len){
            Node start = next;
            for(int i = 1; i<len; i++){
                start = start.next;
            }
            return start;
        }

    }

    SinglyLinkedListApply5(){
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
    void appendLoop(int data){
        if(data < 3){
            return;
        }
        Node n = header;
        Node end = null;
        int num = new Random().nextInt(data);
        num = num > data - 2? data-2:num;
        for(int i=0;i<data;i++){
            Node node = new Node(new Random().nextInt(10));
            n.next = node;
            n = n.next;
        }
        Node n2 = header;
        for(int i=0;i<num;i++){
            n2 = n2.next;
        }
        n.next = n2;
        System.out.println(n.next);
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

    Node getFirst(){return header.next;}

    Node getLast(){
        Node n = header;
        while(n.next != null){
            n = n.next;
        }
        return n;
    }

    private static void appendNode(Node n1, Node n2, int data){
        Node node = new Node(data);
        n1.next = node;
        n2.next = node;
    }

    private static int getLength(Node n){
        int total=0;
        while( n != null ){
            total++;
            n = n.next;
        }
        return total;
    }

    //응용 1교차점 찾기 : 길이를 같게 만들어 한번의 순환으로 찾기
    private static Node getCross(Node n1, Node n2){
        if(n1==null&&n2==null){
            return null;
        }
        int l1 = getLength(n1);
        int l2 = getLength(n2);
        if(l1 > l2){
            n1 = n1.get(l1-l2);
        }else if(l1 < l2){
            n2 = n2.get(l2-l1);
        }

        Node result = null;
        while(n1!=null&&n2!=null){
            if(n1 == n2){ //값이 아닌 값은 주소로 찾아야함
                result = n1;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return result;
    }

    //응용 2. Loop find : 폭이 다른 두 포인터가 만나는 지점이 루프의 시작
    private static Node loopFind(Node node){
        Node fast = node;
        Node slow = node;
        Node result = null;
        while(fast != null && fast.next != null){
            //첫번째 교차는 폭을 다르게
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                break;
            }
        }
        if(fast ==null || fast.next == null){ //loop유무 확인
            return null;
        }

        slow = node; //느린 포인터는 다시 원점에서
        while(fast != slow) {
            //두번째 교차는 폭을 같게
            fast = fast.next;
            slow = slow.next;
        }
        System.out.println(fast);
        return fast;
    }

    public static void main(String[] args){
        SinglyLinkedListApply5 ll1 = new SinglyLinkedListApply5();
        for(int i=0;i<950;i++){
            ll1.append(new Random().nextInt(10));
        }

        SinglyLinkedListApply5 ll2 = new SinglyLinkedListApply5();
        for(int i=0;i<950;i++){
            ll2.append(new Random().nextInt(10));
        }

        for(int i=0;i<50;i++){
            int num = new Random().nextInt(10);
            appendNode(ll1.getLast(),ll2.getLast(),num);
        }

        //응용 1. 교차점
        long before = System.currentTimeMillis();// 많이 오래걸미
        System.out.println("판정 : "+getCross(ll1.getFirst(),ll2.getFirst()));
        long after = System.currentTimeMillis();
        System.out.println("실행시간(ms) : "+(after-before));

        SinglyLinkedListApply5 ll3 = new SinglyLinkedListApply5();
        ll3.appendLoop(300);

        //응용 2. Loop find
        long before2 = System.currentTimeMillis();
        System.out.println("판정 : "+loopFind(ll3.getFirst()));
        long after2 = System.currentTimeMillis();
        System.out.println("실행시간(ms) : "+(after2-before2));
    }
}
