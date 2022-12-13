package practice.Hash;

import java.util.LinkedList;

/**
 * HashTable
 */
class HashTable{
    // 충돌을 방지하기 위해, 배열방에 바로 저장하지 않는다.
    // 배열방 안에 LinkedList을 선언하여 LinkedList에 먼저 저장한다.
    LinkedList<Node> [] data;

    class Node{
        String key;
        String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    HashTable(int sizs) {
        this.data = new LinkedList[sizs];
    }

    // index를 hashcode로 변환
    int getHashCode(String key) {
        int hashCode = 0;
        for (char c : key.toCharArray()) { // 아스키 값을 가지고 옴
            hashCode += c;
        }
        return hashCode;
    }

    // Hashcode를 index로 변환
    int covertToIndex(int hashCode) {
        return hashCode % this.data.length;
    }

    Node searchKey(LinkedList<Node> list, String key) {
        if (list == null) return null;
        for (Node node : list) {
            if (node.key.equals(key)) return node;
        }
        return null;
    }

    void put(String key, String value) {
        int hashCode = getHashCode(key);
        int index = covertToIndex(hashCode);
        LinkedList<Node> list = this.data[index];
        if (list == null) {
            list = new LinkedList<>();
            data[index] = list;
        }
        Node node = searchKey(list, key); // 이미 존재하는지 확인
        if (node == null) list.addLast(new Node(key,value));
        else node.setValue(value);
    }

    String get(String key) {
        int hashcode = getHashCode(key);
        int index = covertToIndex(hashcode);
        LinkedList<Node> list = this.data[index];
        Node node = searchKey(list, key);
        return node == null ? "Not Found" : node.value;
    }
}

public class Hash_Table_Basic {
    public static void main(String[] args) {
        HashTable h = new HashTable(3);
        h.put("Sung","model");
        h.put("jin","singer");
        h.put("Hee","teacher");
        System.out.println(h.get("Sung"));
        System.out.println(h.get("jin"));
        System.out.println(h.get("Hee"));
        System.out.println(h.get("Sung2"));

    }
}
