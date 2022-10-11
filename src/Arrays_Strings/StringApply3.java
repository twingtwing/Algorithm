package Arrays_Strings;

/**
 * 응용 : 주어진 두개의 문자열이 서로 치환되는지를 알아내는 함수를 구현
 *       이때, 대소문자를 구분하고, 공백을 문자로 인정
 *       - 경우 1 : 두개의 문자열을 정렬하고, 앞에서 부터 비교
 *       - 경우 2 : 별도의 배열 공간 사용(이 경우, 아스키 코드만 사용하였을 경우에만 가능)
 *
 */
public class StringApply3 {
    public static void main(String[] args) {
        System.out.println(permutation("AAC","CAA"));
    }

    // 두개의 문자열을 정렬하고, 앞에서 부터 비교
    private static String sort(String str){
        char[] content = str.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content); //정렬된 문자 배열을 문자열로 변환
    }

    private static boolean permutation(String s, String t){
        if (s.length() != t.length()) return false;
        return sort(s).equals(sort(t)); // 문자열 비교함수를 통해 비교가능(굳이 for구문 필요없음)
    }

    // 별도의 배열 공간 사용
    private static boolean permutation2(String s, String t){
        if (s.length() !=  t.length()) return false;
        int[] letters = new int[128];
        for (int i = 0; i < s.length(); i++){
            letters[s.charAt(i)]++;
        }
        for (int i = 0; i < t.length(); i++){
            letters[t.charAt(i)]--;
            if (letters[t.charAt(i)] < 0 ) return false;
        }
        // 길이가 같음을 먼저 확인하였기 때문에, 배열의 전체 합은 무조건 0이다.
        // 즉, 어디가 +이면, 어느 곳은 무조건 -이가 생긴다.
        // 그렇기에 마지막에 굳이 한번 더 +인 곳을 찾을 필요가 없다.
        return true;
    }
}
