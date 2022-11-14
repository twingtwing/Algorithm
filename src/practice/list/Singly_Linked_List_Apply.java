package practice.list;

/**
* 응용 1. 중복값 제거
 *    2. 지정 위치 노드 호출
 *    3. header을 모를 경우, 특정 위치의 노드 삭제 (단, 순회하지 않고 삭제)
 *    4.  x값을 기준으로 값이 작은것은 왼쪽, 큰것들은 오른쪽으로 두 파트로 분리
*/
class SinglyLinkedList2{

    Node header;

    static class Node{
        int data;
        Node link;
    }

    SinglyLinkedList2(){
        header = new Node();
    }

    Node getFirst(){
        return this.header.link;
    }

    Node get(int num){
        Node node = this.header.link;
        while (node != null){
            if (node.data == num)
                return node;
            node = node.link;
        }
        return null;
    }

    void append(int data){
        Node next = this.header;
        Node node = new Node();
        node.data = data;
        while (next.link != null){
            next = next.link;
        }
        next.link = node;
    }

    void delete(int data){
        Node next = this.header;
        while (next.link != null){
            if (next.link.data == data)
                next.link = next.link.link;
            else
                next = next.link;
        }
    }

    void retrieve(){
        Node next = this.header.link;
        while (next.link != null){
            System.out.print(next.data + "->");
            next = next.link;
        }
        System.out.print(next.data);
        System.out.println();
    }

    void retrieve(Node next){
        while (next.link != null){
            System.out.print(next.data + "->");
            next = next.link;
        }
        System.out.print(next.data);
        System.out.println();
    }

    //응용 1. 중복제거
    void removeDup(){
        Node node = this.header.link;
        while (node != null && node.link != null){
            Node next = node;
            while (next.link != null){// null.link가 되면 error가 발생하기 때문에 위에서 미리 막아야함
                if (next.link.data == node.data)
                    next.link = next.link.link;
                else
                    next = next.link;
            }
            node = node.link;
        }
    }

    //응용 2. 지정위치 : 뒤에서 k번째 노드 호출
    // 2-1. 재귀호출

    // 2-1-1.값 호출
    void kthToLast(int k){
        kthToLast(this.header, k);
    }

    int kthToLast(Node node, int k) {
        if (node == null) return 0;
        int count = kthToLast(node.link, k) + 1;
        if (k == count){
            System.out.println("뒤에서 "+k+"번째 값 : "+node.data);
        }
        return count;
    }

    // 2-1-2.Node 호출
    class Repository{
        int count;
    }

    Node kToLastNode(int k) {
        return kToLastNode(this.header,k,new Repository());
    }

    Node kToLastNode(Node node, int k, Repository repository) {
        if (node == null) return null;
        Node result =  kToLastNode(node.link, k,repository);
        repository.count++;
        if (repository.count == k)
            return node;
        return result;
    }

    // 2-2. 포인터 : 2개의 포인터
    Node kToLastPointer(int k){
        return kToLastPointer(this.header,k);
    }

    Node kToLastPointer(Node node, int k){
        Node point01 = node;
        Node point02 = node;

        for (int i = 0; i < k; i++){
            if (point01 == null) return null;
            point01 = point01.link;
        }

        while (point01 != null){
            point01 = point01.link;
            point02 = point02.link;
        }

        return point02;
    }

    // 3. 특정 노드 삭제 (단, 순회하지 않고 삭제)
    //    값을 다음 노드와 교체하고, link를 없앰
    boolean deleteNode(Node node){
        if (node == null || node.link == null) return false;

        node.data = node.link.data;
        node.link = node.link.link;
        return true;
    }

    // 4. 2개의 partition으로 분리

    // 4.1 두개의 list로 만든 후 병합
    Node partCombine(Node node, int x){
        Node left = null,right = null,leftLast = null;

        while (node != null){
            Node next = node.link;
            node.link = null; // 무한 loop를 피하기 위해서 사용
            if (node.data <= x){
                if (left == null){
                    left = node;
                    leftLast = node;
                }else{
                    node.link = left;
                    left = node;
                }
            }
            else {
                if (right == null){
                    right = node;
                }else{
                    node.link = right;
                    right = node;
                }
            }
            node = next;
        }

        if(left == null) return right;

        leftLast.link = right;
        return left;

    }

    // 4.2 처음부터 한개의 list에서 병합
    Node partOne(Node node, int x){
        Node head = new Node();
        Node tail = null;

        while (node != null){
            Node next = node.link;
            node.link = null;
            if (node.data <= x){
                if (head.link == null) tail = node;
                node.link = head.link;
                head.link = node;
            }
            else{
                if (head.link == null){
                    head.link = node;
                }else{
                    tail.link = node;
                }
                tail = node;
            }
            node = next;
        }
        return head.link;
    }

}

public class Singly_Linked_List_Apply {
    public static void main(String[] args) {

        SinglyLinkedList2 ll = new SinglyLinkedList2(); //헤드 노드 작성
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
        ll.removeDup();
        ll.retrieve();

        // 2.1 재귀호출
        // 노드 값 호출
        ll.kthToLast(2);

        // 노드 호출
        int k = 3;
        SinglyLinkedList2.Node n = ll.kToLastNode(k);
        System.out.println("뒤에서 "+ k+"번째 값 : "+n.data);

        // 2.2 포인터
        SinglyLinkedList2.Node n2 = ll.kToLastPointer(k);
        System.out.println("뒤에서 "+ k+"번째 값 : "+n2.data);

        ll.deleteNode(ll.get(2));
        ll.retrieve();

        System.out.println();

//        ll.retrieve(ll.partCombine(ll.getFirst(),3));
        ll.retrieve(ll.partOne(ll.getFirst(),3));
    }
}
