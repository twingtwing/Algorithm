package practice.string;
/**
 * StringBuilder 응용
 * 문자열 압출(String Compression)
 * : 반복되는 문자를 숫자로 압축해서 반환하는 알고리즘
 * => String은 값을 수정하는 과정에서 계속적으로 순회가 일어나기 때문에 비효율적이다.
 * => StringBuilder은 값을 수정하는 과정에서는 효율적이지만,
 *    공간 부족시에  Doubling작업을하기 때문에 비효율적이다.
 * => StringBuilder하는 동시에 문자열의 길이를 사전에 미리 전달하면,
 *    공간부족을 줄이기 때문에 Doubling작업의 비효율을 줄일 수 있다.
 */

public class String_Builder_Apply {

    private static String stringCompression(String str) {
        String newStr = compress(str);
        // 압축이 전혀 되지 않은 경우
        return str.length() < newStr.length() ? str : newStr;
    }

    private static String compress(String str) {
        int count = 0;
        StringBuilder strBuilder = new StringBuilder(getTotal(str));
        for (int i = 0; i < str.length(); i++) {
            count ++;
            // 마지막이거나 다음의 문자와 같지 않을 경우
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i+1)){
                if (count > 1) strBuilder.append(count);
                strBuilder.append(str.charAt(i));
                count = 0;
            }
        }
        return strBuilder.toString();
    }

    // 압축 되었을 경우의 문자열 길이 미리 예측
    private static int getTotal(String str) {
        int count = 0;
        int total = 0;
        for (int i = 0; i < str.length(); i++) {
            count ++;
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i+1)){
                total += String.valueOf(count).length() + 1;
                count = 0;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(stringCompression("aaabbbbcccdddd"));
        System.out.println(stringCompression("abcdd"));

    }
}
