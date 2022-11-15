package practice.list;

import 엔지니어_대한민국.LinkedList.SinglyLinkedListApply5;

import java.util.Random;
import java.util.Stack;

/**
 * 응용 1. LinkedList의 노드들이 회문(palindrome)인지를 확인하는 알고리즘
 * 응용 2. 주어진 두개의 단방향 Linked List에서 교차노드를 찾는 알고리즘(단, 교차점은 주소로 찾아야함)
 * 응용 3. 루프가 있는 확인하고, 시작되는 지점을 Node를 찾는 알고리즘
 */
class SinglyLinkedListApply03{
    
    Node header;
    
    static class Node{
        int data;
        Node link;
        Node(){}
        
        Node(int data){
            this.data = data;
        }

        Node get(int len){
            Node start = link;
            for(int i = 1; i<len; i++){
                start = start.link;
            }
            return start;
        }
    }

    SinglyLinkedListApply03(){
        this.header = new Node();
    }

    void append(int num){
        Node n = header;
        Node end = new Node();
        end.data = num;
        while(n.link != null){
            n = n.link;
        }
        n.link = end;
    }
    void delete(int data){
        Node n = header;
        while(n.link != null){
            if(n.link.data == data){
                n.link = n.link.link;
            }
            else{
                n = n.link;
            }
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

    void retrieveNode(Node node){
        Node n = node;
        while(n.link != null){
            System.out.print(n.data + "->");
            n = n.link;
        }
        System.out.println(n.data);
    }

    Node getFirst(){return this.header.link;}

    Node getLast(){
        Node node = this.header.link;
        while (node != null && node.link != null){
            node = node.link;
        }
        return node;
    }

    boolean isEmpty(){return this.header.link == null;}

    // palindrome create
    static class PalStorage{
        Node node;
    }

    // 중간에 값을 삽입
    void palAppend(int data, PalStorage palStorage){
        if (isEmpty()){
            palStorage.node = this.header;
        }
        palStorage.node = doubleAppend(palStorage.node,data);

    }

    Node doubleAppend(Node prev, int data){
        Node node = new Node(data);
        node.link = new Node(data);
        node.link.link = prev.link;
        prev.link = node;
        return node;
    }

    // 1.palindrome find

    // 1.1 reverse equal
    boolean isPalindromeRev(Node head){
        Node rev = revClone(head);
        retrieveNode(rev);
        return isEqual(head,rev);
    }

    Node revClone(Node head){
        if (head == null || head.link == null) return head;
        Node result = null;
        Node prev = null;
        while (head != null){
            prev = new Node(head.data);
            prev.link= result;
            result = prev;
            head = head.link;
        }
        return result;
    }

    boolean isEqual(Node origin, Node clone){
        if (origin == null || clone == null) return false;
        while (origin != null && clone != null){
            if (origin.data != clone.data) return false;
            origin = origin.link;
            clone = clone.link;
        }
        return true;
    }

    //응용 1.2 폭이 다른 두 개의 포인터 + stack(선입선출)
    //폭이 다른 두 개의 포인터로 중앙 위치를 파악할 수 있음
    //최근에 넣은값을 꺼내야하기 때문에 stack을 사용해야함
    boolean isCheckPiointer(Node head){
        if (head == null) return false;
        if (head.link == null) return true;
        Node one = head;
        Node two = head;
        Stack<Integer> stack = new Stack<>();
        while (two.link != null && two.link.link != null){
            stack.push(one.data);
            one = one.link;
            two = two.link.link;
        }
        stack.push(one.data);

        if(two.link != null && two.link.link == null){
            one = one.link;
        }

        while (one != null){
            int num =stack.pop();
            if (one.data != num) return false;
            one = one.link;
        }
        return true;
    }

    //응용 1.3 중간까지 재귀호출 + 별도의 공간
    class Storage{
        Node node; //중간 이후의 노드를 저장
        boolean result;

        Storage(Node node, boolean result){
            this.node = node;
            this.result = result;
        }
    }

    boolean isPalindromeSt(Node head){
        if (head == null) return false;
        if (head.link == null) return false;
        int length = getLength(head);
        Storage storage = isPalindromeRec(head, length);
        return storage.result;
    }

    int getLength(Node node){
        if (node == null) return 0;
        int count = 0;
        while (node != null){
            count ++;
            node = node.link;
        }
        return count;
    }

    // length를 Node 대신 포인터로 사용하고 이씅ㅁ
    // 위에서와 마찬가지로 -2를 통해 중간 위치를 유추할 수 있다.
    Storage isPalindromeRec(Node node, int length){
        //중간까지만 재귀함수가 감
        if (length == 0) return new Storage(node,true); //짝수
        else if (length == 1) return new Storage(node.link,true); //홀수

        Storage storage = isPalindromeRec(node.link, length-2);
        
        if (!storage.result || storage.node == null){
            return storage;
        }
        storage.result = (node.data == storage.node.data);
        storage.node = storage.node.link;
        return storage;
    }
    
    // 응용 2. 중간에 합쳐지는 교차점 Search : 끝지점이 같기 때문에 끝지점으로 부터 길이를 서로 동일하게 한다.
    Node getCross(Node one, Node two){
        if (one == null || two == null) return null;
        int len01= getLength(one);
        int len02= getLength(two);

        if (len01 > len02){
            one = one.get(len01 - len02);
        }else if(len01 < len02){
            two = two.get(len02 - len01);
        }

        Node result = null;
        while (one != null && two != null){
            if (one == two) result = one;
            one = one.link;
            two = two.link;
        }
        return result;
    }

    // 응용 3.Loop find
    Node loopFind(Node head){
        return null;
    }

}

public class Singly_Linked_List_Apply03 {
    public static void main(String[] args) {
        SinglyLinkedListApply03 sL = new SinglyLinkedListApply03();
        SinglyLinkedListApply03.PalStorage palStorage = new SinglyLinkedListApply03.PalStorage();
        for (int i = 0; i < 4; i++){
            sL.palAppend(new Random().nextInt(10),palStorage);
        }
        palStorage.node.link = palStorage.node.link.link;
        sL.retrieve();

        System.out.println("1. 판정 : "+ sL.isPalindromeRev(sL.getFirst()));
        System.out.println("2. 판정 : "+ sL.isCheckPiointer(sL.getFirst()));
        System.out.println("3. 판정 : "+ sL.isPalindromeSt(sL.getFirst()));
    }
}
