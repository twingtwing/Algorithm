package 엔지니어_대한민국.LinkedList;
/*
*  단방향 Linked List ( SinglyLinkedList )
*/
class SLList {

    Node header; //헤더는 데이터로 사용되지 않고, 관리용도로만 사용됨

    // 노드클래스를 linkedlist클래스가 감싸는 형태
    // 헤더에 데이터가 아닌 시작용도로 사용해야 하기 때문에, 데이터가 아닌 따로 생성해야하기 때문에 list가 node가 감싸도록 함
    static class Node {
        int data;
        Node next = null; // 다음 노드를 지칭하는 주소값
    }

    SLList(){
        header = new Node(); //헤더 초기화
    }

    void append(int data){
        Node end = new Node();
        end.data = data; //데이터 할당
        Node n = header; //포인터가 헤더를 지칭
        while(n.next != null){ //맨 마지막 노드를 찾아야 하므로
            n = n.next;
        }
        n.next = end; // n에 마지막 노드 값이 들어가고 그걸 추가함 노드에 저장시킨다.
    }

    void delete(int data){
        Node n = header; //포인터가 헤더를 지칭
        while(n.next != null){
            if(n.next.data == data) {
                // while조건은 마지막 노드가 가지 않기때문에 전 노드의 주소값을 보고 확인해야함
                // 첫번째 노드를 지칭하지 못하는데 어차피 첫번째 노드는 header이므로 상관이 없다.
                n.next = n.next.next; // 노드를 삭제하지 않고, 주소값만 수정함
            }else{
                n = n.next;
            }
            //단점 : 삭제하고도 끝까지 진행함
        }
    }

    void retrieve(){
        Node n = header.next;
        while(n.next != null){
            System.out.print(n.data + "->");
            n = n.next;
        }
        System.out.println(n.data); //조건이 마지막 노드까지 가지 않기 때문에
    }

}

public class SinglyLinkedList {
    public static void main(String[] args){
        long beforeTime = System.currentTimeMillis();

        SLList ll = new SLList(); //헤드 노드 작성
        ll.append(1);
        ll.append(2);
        ll.append(3);
        ll.append(4);
        ll.retrieve();
        ll.delete(1);
        ll.retrieve();

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
        System.out.println("시간차이(ms) : "+secDiffTime);
    }
}
