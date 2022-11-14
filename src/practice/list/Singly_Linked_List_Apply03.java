package practice.list;

import java.util.Random;

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

    // 1.palindrome find : 중복값이 없다는 조건

    // 1.1 reverse equal
    boolean isPalindromeRev(Node head){
        Node rev = revClone(head);
        retrieveNode(rev);
        return isEqual(head,rev);
    }

    Node revClone(Node head){
        if (head == null || head.link == null) return head;
        Node node = head.link;
        Node result = head;
        Node start;
        while (node != null){
            start = node;
            start.link = result;
            result = start;
            node = node.link;
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

    //응용 1.3 재귀함수



}

public class Singly_Linked_List_Apply03 {
    public static void main(String[] args) {
        SinglyLinkedListApply03 sL = new SinglyLinkedListApply03();
        SinglyLinkedListApply03.PalStorage palStorage = new SinglyLinkedListApply03.PalStorage();
        for (int i = 0; i < 4; i++){
            sL.palAppend(new Random().nextInt(10),palStorage);
        }
        sL.append(3);
        sL.retrieve();
    }
}
