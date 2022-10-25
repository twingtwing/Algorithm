package 엔지니어_대한민국.BigO;
/**
 * 응용 : 주어진 문자열의 길이 k를 가지고 길이가 k인 정렬된 문자열의 모든 알파벳 조합을 만드는 알고리즘
 *       모든 문자열을 만든 후 정렬이 되어있는지 확인을 해야함
 *       => 이 경우에는 시간복잡도는 알파벳의 갯수가 c이면 시간복잡도는 O(c^k)이 걸린다.
 *          여기서 확인하는 작업까지 더하면, 시간복잡도는 O(k*c^k)이 걸린다. 왜냐하면, 정렬하는것이 아니라 정렬유무만 확인하기 때문에 앞의 문자와만 비교하면 된다.
 */

public class StringApply {
    public static final int C = 26;

    public static void main(String[] args) {
        pringSortedStrings(2);
    }

    private static void pringSortedStrings(int k){
        pringSortedStrings(2,"");
    }

    private static void pringSortedStrings(int k, String prefix){
        if (k == 0){ //경우의 수 중 1개 완성
            if (isInOrder(prefix)){ //정렬된 숫자인지 확인
                System.out.println(prefix);
            }
        }else{
            for (int i = 0; i < C; i++){
                char c = itLetter(i); //문자열을 받아옴
                pringSortedStrings(k - 1, prefix + c);
            }
        }

    }

    private static boolean isInOrder(String s){
        for (int i = 1; i < s.length(); i++){
            int prev = itLetter(s.charAt(i - 1));
            int curr = itLetter(s.charAt(i));
            if (prev > curr) return false;
        }
        return true;
    }

    private static char itLetter(int i){ //i번째 문자열가지고 오는 함수
        return (char) (((int) 'a') +i);
    }
}
