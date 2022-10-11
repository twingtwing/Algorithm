package Arrays_Strings;

import java.util.HashMap;

/**
 * 응용 : 주어진 문자열의 문자들이 모두  고유한지를 확인하는 알고리즘
 *        또한, 별도의 저장공간을 사용하지 못하는 경우에는 어떻게 해결할지도 추가로 설명
 *      경우1, ASCII코드로만 이루어진 경우에는 아스키코드 총 갯수인 128개 만큼의 배열 방을 만들어서
 *             문자열을 도는 동안 해당하는 방을 true라고 하고 이때, 갔는데 이미 true이면 고유하지 않음
 *             => 아스키 코드의 총 갯수와 특징을 알면 단 한번의 loop로도 가능하다.
 *      경우2. 아스키코드로만 이루어지지 않은 경우
 *            아스키토드 - 128, Extended 아스키코드 - 256, Unicode - 2^20 + 2^16
 *            이렇게 많은 경우는 배열이 아닌 HashMap을 통하여 구현하는 것이 좋음
 *      경우3, 소문자로만 이루어진 경우, 알고리즘 구현
 *            => 비트 연산자를 이용해서 문제 해결
 *            => Bit Operator : 하나의 연산자에 여러가지 의미를 담고 싶을때 사용
 *                               저장 할때, 2의 거듭 제곱으로 저장한다.
 *                               이렇게 되면, 여러개의 코드를 더해도 고유한 숫자가 나옴
 *                               그러면 그 고유한 숫자 즉 하나의 연산자에 여러가지 의미를 담을 수 있음
 *                               그러면 2^26을 구현해야하지만, 이는 2진법을 쉬프트 연산자로 표현이 가능핟.
 *                               또한, int의 최대 크기는 2^31?까지 이므로 소문자까지만은 가능하다.
 *                               (a-z) => 아스키 코드 (97 - 122 ) => (0 - 25)로 변환해서 사용
 *      경우4, 별도의 공간을 사용하지 않을 경우, O(n^2)만큼 걸리거나 퀵정렬하여 바로 옆의 값과 비교해서 확인(o(nlogn)의 시간이 걸림
 */
public class StringApply {
    public static void main(String[] args) {
        System.out.println(isUnique("String"));
        System.out.println(isUnique2("String;;"));
        System.out.println(isUnique2("string"));

    }

    // 아스키 코드로만 이루어진 경우 + 별도의 공간 사용
    private static boolean isUnique(String str){
        if (str.length() > 128) return false; // 최소 1개 이상인것이므로 바로 falae
        boolean[] char_set = new boolean[128];
        for (int i = 0; i <str.length(); i++){
            int val = str.charAt(i);
            if (char_set[val]){ //이전에 존재했던 값이기 때문
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    // 아스키코드 말고도 다른 코드도 포함해서 이루어진 경우 + 별도의 공간 사용
    private static boolean isUnique2(String str){
        HashMap<Integer,Boolean> hashMap = new HashMap<>();
        for (int i = 0; i <str.length(); i++){
            int val = str.charAt(i);
            if (hashMap.containsKey(val)){ // 기존에 추가한 키인지 확인
                return false;
            }
            hashMap.put(val,true);
        }
        return true;
    }

    // 소문자로만 이루어진 경우 + 별도의 공간 사용
    private static boolean isUnique3(String str){
        int checker = 0;
        for (int i =0; i< str.length(); i++){
            int val = str.charAt(i ) - 'a'; // int로 자동변환되면 97이 마이너스됨
            if((checker & (i << val)) > 0){ //i를 해당 val만큼 쉬프트하고, 해당값이 checker에 있느지 비트연산자로 확인
                return false;
            }
            checker |= (1 << val); //쉬프트한 값을 checker에 저장
                                   // |은 둘 중에 하나만 1이라도 1을 저장하기에 양쪽에 1로 마크된 숫자가 머지됨?
        }
        return true;
    }
}
