package Arrays_Strings;
/**
 * String에 문자를 붙이는 과정은 문자를 모두 스캔한 후에 마지막에 붙임
 * 즉, 문자배열을 돌때, x를 문자의 길이라고 한다면, x + 2x + 3x ..nx 이므로 O(xn^2)의 괒어이 걸림
 * 비효율적이기 때문에, 자바에서는 StringBuilder라는 클래스를 제공해줌
 * StringBuilder는 클래스안에 미리 배열공간을 만들어놓고, append라는 추가 함수를 호출하면,
 * 문자를 돌지 않고, 바로 추가함
 * 공간은 배열이기에 추가할때 공간이 부족하면, ArrayList처럼 공간을 늘리는 작업을 한다.
 * 시간복잡도와 공간복잡도가 매우 현저히 줄어둠
 *
 * 추가적으로 StringBuffer가 있는데 StringBuilder는 동기화를 지원하지 않지만, StringBuffer는 동기화를 지원함...?
 * 그렇기 때문에 속도가 느릴수 있지만, 멀티 Thread환경에서는 동기화를 보장해야하기에 사용함
 */
class StringBuilder{
    private char[] value;
    private int size;
    private int index;

    StringBuilder(){
        size = 1;
        value = new char[size];
        index = 0;
    }

    public void append(String str){
        if (str == null) str = "null";
        int len = str.length();
//        ensureCapacity(len);
        for (int i = 0; i < str.length(); i++){
        }
    }
}

public class StringBuilders {
    public static void main(String[] args) {

    }
}
