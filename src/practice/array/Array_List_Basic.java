package practice.array;

class ArrayListBasic {
    private int size;
    private int index;
    private Object[] ary;

    public ArrayListBasic() {
        this.size = 1;
        this.index = 0;
        this.ary = new Object[this.size];
    }

    public void add(Object obj) {
        if (isFull()) doubling();
        ary[this.index++] = obj;
    }

    private boolean isFull() {
        return this.index == this.size - 1;
    }

    private void doubling() {
        System.out.println(this.size + "=>" + this.size * 2);
        this.size = this.size * 2;
        Object[] douAry = new Object[this.size];
        for (int i = 0; i < this.ary.length; i++) douAry[i] = this.ary[i];
        this.ary = douAry;
    }

    public void remove(int index) { // 순차적인 자료구조이기 때문에 배열을 앞당겨야한다.
        if (index > this.size -1 || index < 0) throw new IndexOutOfBoundsException();
        for (int i = index; i < this.size - 1; i++) {
            this.ary[index] = this.ary[index + 1];
        }
        this.index--;
    }

    public Object get(int index) {
        if (index > this.size -1 || index < 0) throw new IndexOutOfBoundsException();
        return this.ary[index];
    }
}

public class Array_List_Basic {
    public static void main(String[] args) {
        ArrayListBasic list = new ArrayListBasic();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");

        System.out.println(list.get(5));
        list.remove(5);
        System.out.println(list.get(5));
    }
}
