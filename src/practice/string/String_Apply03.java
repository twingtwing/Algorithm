package practice.string;

import static java.util.Arrays.sort;

/**
 * 응용 : 주어진 문자열이 회문을 만들 수 있는 문자열의 치환인지 확인하는 알고리즘
 *       => 회문 : 앞뒤로 읽어도 같은 문자
 *       => 치환 : 위치를 변경한 문자
 *       => 회문의 조건으로 홀수개를 가진 문자는 1개 이하여야한다.
 * 1. 배열 순회 + 홀수 카운드 따로
 * 2. 배열 순회와 홀수 카운트를 동시에
 * 3. 비트 연산자 사용
 */
public class String_Apply03 {

    // 배열을 만드는 순회와 홀수 count를 따로 하기 때문에 꼭 1번은 순회한다.
    // 시간복잡도 : O(n)
    private static boolean isPerOfPal(String str) {
        int [] table = countChar(str);
        return countOdd(table);
    }

    private static int[] countChar(String str) {
        // Character.getNumericValue() : char형을 int형으로 변환
        int [] table = new int[Character.getNumericValue('z')
                - Character.getNumericValue('a') + 1]; // a ~ z, 공배까지의 배열방을 만듦
        for (char c : str.toCharArray()){
            int n = getCharNumber(c);
            if (n != -1) table[n]++; // 소문자를 제외한 문자는 제외
        }
        return table;
    }

    private static int getCharNumber(char c) {
        int z = Character.getNumericValue('z');
        int a = Character.getNumericValue('a');
        int n = Character.getNumericValue(c);
        if (n >= a && n <= z) return n - a;
        return -1;
    }

    private static boolean countOdd(int[] table) {
        int count = 0;
        for (int n : table) {
            if (n % 2 == 1) count++;
            if (count > 1) return false;
        }
        return true;
    }

    // 2.배열 순회와 홀수 count를 동시에 하기 때문에 위의 방법보다는 적게 걸릴 가능성이 있다.
    private static boolean isPerOfPal2(String str) {
        int count = 0;
        int [] table = new int[Character.getNumericValue('z')
                - Character.getNumericValue('a') + 1];
        for (char c : str.toCharArray()){
            int n = getCharNumber(c);
            if (n == -1) continue;
            table[n]++;
            // 홀수 갯수면 결과적으로는 1이 남는다.
            if (table[n] % 2 == 1) count ++;
            // 짝스 갯수면 결과적으로는 0이 남게 된다.
            else count --;
        }
        // 회문이 될려면 홀수는 1개 밖에 없어야하므로 1 이하여야 한다.
        return count <= 1;
    }

    // 3. Bit Operator (비트 연산자)
    // 정수를 이준수 배열방으로 생각하고 (짝수는 0, 홀수는 1) 마지막에 1이 몇개인지 확인
    // & : 짝수를 0으로 만들 수 있는 연산자
    // ..===> 나중에


    public static void main(String[] args) {

    }
}
