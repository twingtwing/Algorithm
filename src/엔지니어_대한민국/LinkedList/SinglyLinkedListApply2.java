package 엔지니어_대한민국.LinkedList;
/*
 *  단방향 Linked List ( SinglyLinkedList ) 응용
 *  1. 특정 위치의 노드를 삭제 (닽, 삭제할 노드만 가지고 있음)
 *     : 단방향이기 때문에 삭제할 노드의 앞의 위치는 알 수 없음
 *  2. x값을 기준으로 값이 작은것은 왼쪽, 큰것들은 오른쪽으로 두 파트로 나누기
 *     (단, x는 오른쪽 파트 어디에 놔도 상관없음)
 */
class SLApply2{
    Node header;

    static class Node {
        int data;
        Node next = null;
    }

    SLApply2(){
        header = new Node();
    }

    void append(int data){
        Node end = new Node();
        end.data = data;
        Node n = header;
        while(n.next != null){
            n = n.next;
        }
        n.next = end;
    }

    void delete(int data){
        Node n = header;
        while(n.next != null){
            if(n.next.data == data) {
                n.next = n.next.next;
            }else{
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

    void retrieve(Node n){
        while(n.next != null){
            System.out.print(n.data + "->");
            n = n.next;
        }
        System.out.println(n.data);
    }

    Node get(int num){
        Node n = header.next;
        while(n.next != null){
            if(n.data == num){
                return n;
            }
            n = n.next;
        }
        return n;
    }

    // 응용 1. hear 등 아무런 정보를 모를 경우의 특정노드 정보만으로 특정토드를 삭제할 경우
    // 특정노드와 특정노드 다음 노드와 데이터 값을 교환한 후, 다음노드를 삭제하면 된다.
    boolean deleteNode(Node n){
        if(n == null || n.next == null){
            return false;
        }
        n.data = n.next.data; // 데이터 교환
        n.next = n.next.next; // 특정노드의 다음 노드를 대신 삭제
        return true;
    }

    // 응용2
    // 1) 두줄로 divide 한후에 결합 : 병합정렬??
    Node partition(Node n,int x){

        //단점 : 포인터를 4개나 사용하기에 복잡함
        Node left = null;
        Node n1 = null;
        Node right = null;
        Node n2 = null;

        while(n != null){
            Node next = n.next;
            n.next = null;
            if(x > n.data){
                if(left == null){
                    left = n;
                    n1 = left;
                }else{
                    n1.next = n;
                    n1 = n;
                }
            }else{
                if(right == null){
                    right = n;
                    n2 = right;
                }else{
                    n2.next = n;
                    n2 = n;
                }
            }
            n = next;
        }
        if(left == null){
            return right;
        }
        n1.next = right;
        return left;
    }

    // 2) 기존 list에 앞뒤로 붙이기
    Node partition2(Node n,int x){

        Node head = n;
        Node tail = n;

        while(n != null){
            Node next = n.next;
            if(x > n.data){
                 n.next = head;
                 head = n;
            }else{
                tail.next = n;
                tail = n;
            }
            n = next;
        }
        tail.next = null; // tail이 마지막임을 표시
        return head;
    }

}

public class SinglyLinkedListApply2 {
    public static  void main(String[] args){
        SLApply2 ll = new SLApply2(); //헤드 노드 작성
        ll.append(8);
        ll.append(5);
        ll.append(2);
        ll.append(4);
        ll.append(7);
        ll.append(3);
        ll.retrieve();

        // 1. 정보없이 특정 노드 삭제
        ll.deleteNode(ll.get(4));
        ll.retrieve();

        // 2.1 두줄로 세워서 divide
//        ll.retrieve(ll.partition(ll.get(8),5));
        // 2.2 기존 list에 앞뒤로 붙이기
        ll.retrieve(ll.partition2(ll.get(8),5));
    }
}
