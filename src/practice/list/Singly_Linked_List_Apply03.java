package practice.list;

import java.util.Random;
import java.util.Stack;

import static practice.list.SinglyLinkedListApply03.doubleAppend;
import static practice.list.SinglyLinkedListApply03.getCross;

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

    static void doubleAppend(Node one, Node two, int data){
        Node node = new Node(data);
        one.link = node;
        two.link = node;
    }

    void appendLoop(int data, int position){
        Node n = header;
        Node end = new Node(data);
        end.link = n.get(position);
        System.out.println(n.get(position).data);
        while(n.link != null){
            n = n.link;
        }
        n.link = end;
    }

    void retrieve(){
        Node n = header.link;
        while(n.link != null){
            System.out.print(n.data + "->");
            n = n.link;
        }
        System.out.println(n.data);
    }

    void retrieveLen(int len){
        Node n = header.link;
        for (int i = 0; i < len; i++){
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

    static int getLength(Node node){
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
    static Node getCross(Node one, Node two){
        if (one == null || two == null) return null;
        int len01 = getLength(one);
        int len02 = getLength(two);

        if (len01 > len02){
            one = one.get(len01 - len02);
        }else if(len01 < len02){
            two = two.get(len02 - len01);
        }

        Node result = null;
        while (one != null && two != null){
            if (one == two) {
                result = one;
                break;
            }
            one = one.link;
            two = two.link;
        }
        return result;
    }

    // 응용 3.Loop find
    /**
     * 직선의 길이 : n / loop의 길이 : m
     * fast는 slow보다 속도를 2배로 설정한다.
     * 그러면 둘다 loop를 진입할때는 fast는 loop 시작점부터 n, slow는 loop 시작점에 있게 된다.
     * 두 포인터가 같은 위치가 만나는 경우는 fast가 slow를 따로 잡을 때다.
     * 그렇기에 loop를 직선으로 가장하면, fast의 위치는 n, slow의 위치를 m으로 생각할 수있다.
     * slow - fast = m - n 가 되고, fast가 2(m - n), slow가 m - n을 더 가면 같은 위치에 있게 되고,
     * 총 길이는 2m-n 이므로 loop의 실제 위치는 (2m -n)%m  = m - n이 된다.
     * => 여기에서 관점은 loop에 둘다 동시에 들어가는 시점이고,
     *    그 loop를 직선으로 생각하고, 직선의 길이를 한정하지 않고, 무한대로 생각하여 같은 위치일때
     *    루프의 길이를 나누었던 나머지 값이 loop의 위치라고 생각하면된다.
     */
    Node loopFind(Node head){
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.link != null){
            fast = fast.link.link;
            slow = slow.link;
            if (fast == slow) break;
        }

        // loop가 아니라서 while를 나온 경우
        if (fast == null || fast.link == null) return null;

        slow = head;
        while (fast != slow){
            fast = fast.link;
            slow = slow.link;
        }
        return fast;
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

        SinglyLinkedListApply03 sL01 = new SinglyLinkedListApply03();
        SinglyLinkedListApply03 sL02 = new SinglyLinkedListApply03();
        for (int i = 0; i < 4; i++){
            sL01.append(new Random().nextInt(10));
        }

        for (int i = 0; i < 4; i++){
            sL02.append(new Random().nextInt(10));
        }

        for (int i = 0; i < 4; i++){
            doubleAppend(sL01.getLast(),sL02.getLast(),new Random().nextInt(10));
        }

        sL01.retrieve();
        sL02.retrieve();

        System.out.println("교차점 : " + getCross(sL01.getFirst(),sL02.getFirst()).data);

        SinglyLinkedListApply03 sL03 = new SinglyLinkedListApply03();
        for (int i = 0; i < 9; i++){
            sL03.append(new Random().nextInt(10));
        }
        sL03.appendLoop(new Random().nextInt(10),4);
        sL03.retrieveLen(11);

        System.out.println("loop 시작점 : " + sL03.loopFind(sL03.getFirst()).data);
    }

}
