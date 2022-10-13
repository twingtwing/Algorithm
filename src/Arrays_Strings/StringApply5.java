package Arrays_Strings;
/**
 * 응용 : 단 한번의 실행으로 두개의 문자열이 회전된 문자열인지 확인할 수 있는 알고리즘
 *       회전 : 왼쪽 끝의 문자가 오른쪽 끝으로 옮겨짐. 말 그대로 회전
 *       => 같은 문자열을 붙여주면 중간에 원래 문자열이 생기는 특징을 이용한다.
 *       => 포함여부 contains() 함수로 손쉽게 확인 가능하다.
 */
public class StringApply5 {
    public static void main(String[] args) {
        System.out.println(isRotation("string","trings"));
        System.out.println(isRotation("string","ringst"));
        System.out.println(isRotation("string","ingstr"));
        System.out.println(isRotation("string","ngstri"));
        System.out.println(isRotation("string","gstrni"));
    }

    private static boolean isRotation(String s1, String s2){
        if (s1.length() != s2.length()) return false;
        return isSubString(s1 + s1, s2);
    }

    private static boolean isSubString(String longStr, String shortStr){
        return longStr.contains(shortStr);
    }
}
