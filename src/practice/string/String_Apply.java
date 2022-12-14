package practice.string;

import java.util.Arrays;
import java.util.HashMap;

/**
 * String
 * 응용 1. 주어진 문자들이 모두 고유한지를 확인하는 알고리즘
 * 1) ASCII코드로만 이루어진 경우
 * 2) 아스키코드로만 이루어지지 않은 경우
 *    (아스키토드 - 128, Extended 아스키코드 - 256, Unicode - 2^20 + 2^16)
 * 3) 소문자로만 이루어진 경우
 * 4) 별도의 공간을 사용하지 않는 경우
 *     O(n^2) 혹은 퀵정렬하여  O(nlogn)의 시간일 걸린다.
 *
 * 응용 2. 주어진 2개의 문자열이 서로 치환되는지를 알아내는 알고리즘 (대소문자 구분, 공백도 문자)
 * => 치환 : 모든 문자열이 일치함을 의미한다.
 * 1) 2개의 문자열을 정렬하고, 앞에서부터 비교
 * 2) 별도의 배열 공간을 사용
 *    => 공간을 사용하기 때문에 아스키 코드만 하였을때 경우에만 가능능
 */
public class String_Apply {

    // 응용 1.1 아스키 코드만큼의 배열방을 만들어서 순회한다.
    // 아스키코드의 총 갯수와 특징을 알면 시간복잡도를 O(n)이 가능하다.
    private static boolean isUnique(String str) {
        if (str.length() > 128) return false; // 갯수를 알기 때문에 가능
        boolean[] charSet = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (charSet[val]) return false;
            charSet[val] = true;
        }
        return true;
    }

    // 응용 1.2 아스키 코드 이외의 코드도 포함된 경우
    // 검색속도를 높이기 위해 HashMap을 통하여 구현한다.
    private static boolean isUnique2(String str) {
        HashMap<Integer,Boolean> hashMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (hashMap.containsKey(val)) return false;
            hashMap.put(val,true);
        }
        return true;
    }

    // 응용 1.3 소문자로만 이루어진 경우 ...? 아직도 모르겠음
    // 아스키 코드의 특징와 비트연산자의 특징을 알고 있어야한다.
    // 숫자(0~9) : 48 ~ 57 대문자 : 65 ~ 90 소문자 : 97 ~ 122
    private static boolean isUnique3(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a'; // a를 빼서 0 ~ 25로 변경함 => 이진법으로 11001까지 표현가능
            // i를 val만큼 이동하고, 둘다 모두 1 이면 1을 반환한다.
            // 즉, 해당값이 check에 있는지 확인가능
            if ((checker & (i << val)) > 0) return false;
            // i를 val만큼 이동하고, 둘중 하나가 1 이면 1을 반환한다.
            // 즉, 쉬프트한 값을 check에 저장한다.
            checker |= (1 << val);
        }
        return true;
    }

    // 응용 2.1 정렬 후 비교
    private static boolean permutation(String one, String two){
        if (one.length() != two.length()) return false;
        return sort(one).equals(sort(two));
    }

    private static String sort(String two) {
        char [] strAry = two.toCharArray();
        Arrays.sort(strAry);
        return new String(strAry);
    }

    // 응용 2.2 배열 공간을 이용해서 순회 => 아스키 코드일때만 가능
    private static boolean permutation2(String one, String two){
        if (one.length() != two.length()) return false;
        int [] ascii = new int[128]; // 중복값이 있을 수 있으므로, boolean이 아니라 숫자형으로 함
        for (int i = 0; i < one.length(); i++) ascii[one.charAt(i)] ++;
        for (int i = 0; i < two.length(); i++) {
            ascii[two.charAt(i)] --;
            if (ascii[two.charAt(i)] < 0) return false;
        }
        return true;

    }

    public static void main(String[] args) {
        System.out.println(isUnique("String"));
        System.out.println(isUnique2("String;;"));
        System.out.println(isUnique2("string"));

        System.out.println(permutation("AAC","CAC"));
        System.out.println(permutation2("AAC","CCA"));
    }
}
