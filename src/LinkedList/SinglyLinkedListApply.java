package LinkedList;
/*
 *  단방향 Linked List ( SinglyLinkedList ) 응용
 *  1. 정렬 x,Linked List, 중복값을 제거 ( 단, Buffer X)
 *  2. 끝에서 k번째 노드를 찾는 알고리즘 구현
 */

class SLApply{
    Node header;

    static class Node{
        int data;
        Node next = null;
    }

    SLApply(){
        header = new Node();
    }

    void append(int data){
        Node next = new Node();
        next.data = data;
        Node n = header;
        while(n.next != null){
            n = n.next;
        }
        n.next = next;
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

    Node getFirst(){
        return header.next;
    }

    Node getLast(){
        Node n = header;
        while(n.next == null){
            n = n.next;
        }
        return n;
    }

    // 응용 1.중복값 제거
    void removeDups(){
        Node n = header;
        while(n != null && n.next != null){
            // 여기서 마지막 노드가 중복값이라 삭제할 경우, next로 가면 null이므로 에러가 발생하기에 n!=null체크를 해줘야함??
            Node r = n;
            while(r.next != null){
                if(n.data == r.next.data){
                    r.next = r.next.next; // n를 삭제하지않고, r을 삭제하는 것이 진행하기 좋음
                }else{
                    r = r.next;
                }
            }
            // 단점 : 마찬가지로 끝까지 가기 때문에 데이터가 많으면 시간이 오래걸림
            // 거기다가 이중 반복문이므로 데이터가 많으면 시간은 n^2이 걸림
            // 시간은 오래걸리지만, 그러나 공간 효율성이 있는 알고리즘임
            n = n.next;
        }
    }

    // 응용 2. 지정위치의 노드호출
    // (1) 재귀 호출 : 조건을 만족할때까지 자기를 계속 호출하고, 호출할때 뒤를 같이?? 호출함 즉, 재귀호출하면서 count룰 하면 됨

    // 노드 값 호출
    int kthToLast(Node n, int k){
        if (n==null){
            return 0;
        }

        int count = kthToLast(n.next, k) + 1; // 재귀 호출
        if( count == k){
            System.out.println("뒤에서 "+k+"번째 값 : "+n.data);
        }
        return count;
    }

    //노드 호출
    class Reference{
        public int count;
    }

    Node kthToLast(Node n, int k, Reference r){
        if (n == null){
            return null;
        }

        Node found = kthToLast(n.next, k,  r); // 재귀 호출
        r.count++;
        if( r.count == k){
            return n;
        }
        return found;
    }

    // (2) 포인터 : 노드의 k번째 이동했을때, null 값을 호출
    Node kthToLastPoint(Node first, int k){
        Node p1 = first;
        Node p2 = first;

        for(int i = 0; i < k; i++){
            if(p1 == null){
                return null;
            }
            p1 = p1.next;
        }

        //여기서 이미 p2와 p1의 간격은 k만큼 차이 나기 때문에 p1이 null일때까지 반복분을 돌리면 p2가 지정된 위치를 찾아줌
        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

}

public class SinglyLinkedListApply {

    public static void main(String[] args){
        long before = System.currentTimeMillis();

        SLApply ll = new SLApply(); //헤드 노드 작성
        ll.append(1);
        ll.append(1);
        ll.append(2);
        ll.append(3);
        ll.append(4);
        ll.append(2);
        ll.append(2);
        ll.append(3);
        ll.retrieve();
        // 1.중복값 제거
        ll.removeDups();
        ll.retrieve();

        // 2.1 재귀호출
        // 노드 값 호출
        ll.kthToLast(ll.getFirst(),2);

        // 노드 호출
        int k = 3;
        SLApply.Node n = ll.kthToLast(ll.getFirst(),k, ll.new Reference());
        System.out.println("뒤에서 "+ k+"번째 값 : "+n.data);

        // 2.2 포인터
        SLApply.Node n2 = ll.kthToLastPoint(ll.getFirst(),k);
        System.out.println("뒤에서 "+ k+"번째 값 : "+n2.data);

        long after = System.currentTimeMillis();
        System.out.println("실행시간(ms)" + (after - before));
    }
}
