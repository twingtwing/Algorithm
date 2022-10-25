package 엔지니어_대한민국.Arrays_Strings;

import java.lang.StringBuilder;
/**
 * 문자열 압축(String Compression) : 반복되는 문자를 숫자로 압축해서 반환하는 알고리즘
 *                                 => 이때, count하고 그 값을 수정할때 newStr = newStr + str.get(i) + count 이런식으로 진행하기 되는데
 *                                    앞에서 말했듯이?? 이 방법은 비효율적이다.
 *                                 => 그렇기 StringBuilder을 사용하지만, 이 방법 또한, 공간 부족시에 doubling 작업을 하기에 효율성이 약간 떨어진다.
 *                                 => 이때, 문자열 길이를 StringBuilder 선언 시에 길이를 전달하면, doubling 작업을할 필요가 없다.
 */
public class StringCompression {
    public static void main(String[] args) {
        System.out.println(compressString("aaabbbbcccdddd"));
        System.out.println(compressString("abcd"));
    }

    private static String compressString(String str){
        String newStr = compress(str);
        return str.length() < newStr.length() ? str : newStr; // 반복이 없는 문자열은 그냥 출력하기 위해서
    }

    private static String compress(String str){
        int count = 0;
        StringBuilder newString = new StringBuilder(getTotal(str));
        for (int i=0; i<str.length(); i++){
            count++;
            if ( i+1 >= str.length() || str.charAt(i) != str.charAt(i + 1)){
                newString.append(str.charAt(i));
                newString.append(count);
                count = 0;
            }
        }
        return newString.toString();
    }

    private static int getTotal(String str){
        int count = 0;
        int total = 0;
        for (int i = 0; i < str.length(); i++) {
            count++;
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                total += i + String.valueOf(count).length();
                count = 0;
            }
        }
        return total;
    }
}
