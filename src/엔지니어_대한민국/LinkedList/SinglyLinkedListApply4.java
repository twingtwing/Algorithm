package 엔지니어_대한민국.LinkedList;

import java.util.Random;
import java.util.Stack;
/*
 *  단방향 Linked List ( SinglyLinkedList ) 응용
 *  응용 1. Linked List의 노드들이 회문(Palindrome)인지를 확인하는 알고리즘 구현
 *         (Palindrome 회문 : 앞으로 읽거나 뒤로 읽어도 같다는 의미)
 */
public class SinglyLinkedListApply4 {

    Node header;

    static class Node{
        int data;
        Node next = null;

        Node(){}

        Node(int data){
            this.data = data;
        }

    }

    SinglyLinkedListApply4(){
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

    Node getFirst(){return header.next;}

    //회문 만들기
    private static void retrieveNode(Node n){
        if(n==null){
            return;
        }
        while(n.next != null){
            System.out.print(n.data + "->");
            n = n.next;
        }
        System.out.println(n.data);
    }

    class PalStorage{
        Node start = null;
    }

    void palAppend(int data,PalStorage p){
        if(p.start == null){
            p.start = header;
        }
        p.start = doubleAppend(p.start,data);
    }

    Node doubleAppend(Node n,int data){
        Node node = new Node(data);
        node.next = new Node(data);

        node.next.next = n.next;
        n.next = node;

        return node;
    }

    //응용 1.1 거꾸로 정렬해서 비교
    private static boolean isPalindrome(Node head){
        Node reveresd = reverseAndClone(head);
        return isEqual(head,reveresd);
    }

    private static Node reverseAndClone(Node n){
        Node result = null;
        while(n != null){
            Node node = new Node(n.data);
            node.next = result;
            result = node;
            n = n.next;
        }
        return result;
    }

    private static boolean isEqual(Node n1, Node n2){
        while(n1 != null & n2 != null){
            if(n1.data != n2.data){
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1 == null && n2 == null; // 둘다 마지막 노드인지도 확인
    }

    boolean revSortCheck(){
        Node node = LPadList(header.next);
        Node next = header.next;
        while(next != null || node != null){
            if(next.data != node.data){
                return false;
            }
            next = next.next;
            node = node.next;
        }
        return true;
    }

    private Node insertBefore(Node before,int data){
        Node plus = new Node(data);
        plus.next = before;
        return plus;
    }

    private Node LPadList(Node n){
        Node result = null;
        while(n != null){
            result = insertBefore(result,n.data);
            n = n.next;
        }
        return result;
    }

    //응용 1.2 폭이 다른 두 개의 포인터 + stack(선입선출)
    private static boolean isCheckPiointer(Node head){
        Node fast = head;
        Node slow = head;

        Stack<Integer> stack = new Stack<Integer>(); //스택 : 선입선출

        while(fast != null && fast.next != null){ //짝수, 홀수 고료해야하기 때문
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null){ //홀수 일 경우 가운데 문자 무시해야함
            slow = slow.next;
        }
        while(slow!= null){
            int n = stack.pop(); // 선입선출이기 때문에 가능함
            if(slow.data != n){
                return false;
            }
            slow = slow.next;
        }
        return slow == null;
    }

    //응용 1.3 중간까지 재귀호출
    private static class Storage{
        public Node node;
        public boolean result;

        Storage(Node n, boolean r){ //생성자 : 노드주소, 결과값을 객체에 저장
            node = n;
            result = r;
        }
    }

    private static boolean isPalindromeSt(Node node){
        int length = getListLength(node);
        Storage st = isPalindromeRec(node,length);
        return st.result;
    }

    private static Storage isPalindromeRec(Node node, int length){
        if(node == null || length <=0){ //짝수
            return new Storage(node,true);
        }else if(length ==1){ //홀수
            return new Storage(node.next,true);
        }
        Storage storage = isPalindromeRec(node.next,length - 2);
        if(!storage.result || storage.node == null){
            return storage;
        }
        storage.result = (node.data == storage.node.data);
        storage.node = storage.node.next; //중앙에서 정반대 방향으로 감(그래야 비교가 가능)
        return storage;
    }

    private static int getListLength(Node head){
        int total = 0;
        while(head != null){
            total++;
            head = head.next;
        }
        return total;
    }

    public static void main(String[] args){
        SinglyLinkedListApply4 ll1 = new SinglyLinkedListApply4();
        for(int i=0;i<1000;i++){
            ll1.append(new Random().nextInt(10));
        }

        SinglyLinkedListApply4 ll2 = new SinglyLinkedListApply4();
        SinglyLinkedListApply4.PalStorage p = ll2.new PalStorage();

        for(int i=0;i<5;i++){
            ll2.palAppend(new Random().nextInt(10),p);
        }

        //응용1.1 거꾸로 정렬
        long before = System.currentTimeMillis();// 시간이 확실히 오래걸림 이유 찾기
        System.out.println("판정 : "+ll1.revSortCheck());
        long after = System.currentTimeMillis();
        System.out.println("판정 : "+ll2.revSortCheck());
        System.out.println("실행시간(ms) : "+(after-before));

        long before2 = System.currentTimeMillis();
        System.out.println("판정 : "+isPalindrome(ll1.getFirst()));
        long after2 = System.currentTimeMillis();
        System.out.println("판정 : "+isPalindrome(ll2.getFirst()));
        System.out.println("실행시간(ms) : "+(after2-before2));

        //응용1.2 두개의 포인터
        long before3 = System.currentTimeMillis();
        System.out.println("판정 : "+isCheckPiointer(ll1.getFirst()));
        long after3 = System.currentTimeMillis();
        System.out.println("판정 : "+isCheckPiointer(ll2.getFirst()));
        System.out.println("실행시간(ms) : "+(after3-before3));

        //응용 1.3 중간까지 재귀호출
        long before4= System.currentTimeMillis();
        System.out.println("판정 : "+isPalindromeSt(ll1.getFirst()));
        long after4 = System.currentTimeMillis();
        System.out.println("판정 : "+isPalindromeSt(ll2.getFirst()));
        System.out.println("실행시간(ms) : "+(after4-before4));

    }
}
