package 엔지니어_대한민국.Arrays_Strings;

import java.util.LinkedList;

class HashT{
    class Node{
        String key;
        String value;
        public Node(String key, String value){
            this.key = key;
            this.value = value;
        }
        String getValue(){
            return value;
        }
        void setValue(String value){
            this.value = value;
        }
    }

    LinkedList<Node>[] data; // collison을 방지하기 위해, 배열방에 바로 저장하지 않고, 배열방 안에 linkedlist를 선언하여 linkedlist에 먼저 저장한다.

    HashT(int size){
        this.data = new LinkedList[size];
    }

    int getHashCode(String key){
        int hashCode = 0;
        for (char c : key.toCharArray()){ // 아스키값을 가져옴
            hashCode += c;
        }
        return hashCode;
    }

    int convertToIndex(int hashCode){
        return hashCode % data.length;
    }

    Node searchKey(LinkedList<Node> list, String key){
        if (list == null) return null;
        for (Node node : list){
            if (node.key.equals(key)){
                return node;
            }
        }
        return null;
    }

    void put(String key, String value){
        int hashCode = getHashCode(key);
        int index = convertToIndex(hashCode);
        LinkedList<Node> list = data[index];
        if (list == null){
            list = new LinkedList<>();
            data[index] = list;
        }
        Node node = searchKey(list,key); //값을 가지고 있는지 확인
        if (node == null){ //없으면 node 생성
            list.addLast(new Node(key,value));
        }else {
            node.setValue(value);
        }
    }

    String get(String key){
        int hashCode = getHashCode(key);
        int index = convertToIndex(hashCode);
        LinkedList<Node> list = data[index];
        Node node = searchKey(list,key);
        return node == null ? "Not found" : "key : "+key + ", hashcode : " + hashCode + ", index : " + index + ", value : " + node.getValue();
    }

}

public class HashTable {
    public static void main(String[] args) {
        HashT h = new HashT(3);
        h.put("Sung","model");
        h.put("jin","singer");
        h.put("Hee","teacher");
        System.out.println(h.get("Sung"));
        System.out.println(h.get("jin"));
        System.out.println(h.get("Hee"));
        System.out.println(h.get("Sung2"));
    }
}
