package practice.string;
/**
 * StringBuilder
 */
class StringBuilderBasic{
    private int size;
    private int index;
    private char[] value;

    StringBuilderBasic(){
        this.size = 1;
        this.index = 0;
        this.value = new char[this.size];
    }

    public void append(String str) {
        if (str == null) return;
        strCapacity(str.length());
        for (int i = 0; i < str.length(); i++) value[index++] = str.toCharArray()[i];
    }

    private void strCapacity(int len) {
        if (len == 0) return;
        this.size = this.size + len; // ArrayList와 달리 문자의 길이 만큼 확장한다.
        char[] newVal = new char[this.size];
        for (int i = 0; i < this.value.length; i++) newVal[i] = this.value[i];
        this.value = newVal;
        System.out.println(this.size);
    }

    public String toString(){return new String(this.value,0,index);}

}

public class String_Builder_Basic {
    public static void main(String[] args) {
        StringBuilderBasic str = new StringBuilderBasic();
        str.append("StringBuilder");
        str.append(" is");
        str.append(" Good");
        System.out.println(str.toString());
    }
}
