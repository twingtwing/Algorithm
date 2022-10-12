package Arrays_Strings;
/**
 * 응용 : 세가지 문자열 편집기능이 있다. 문자를 한개 삽입하는 기능(insert)/ 문자를 한개 삭제하는 기능(remove)/ 그리고 하나의 문자를 교체할 수 있는 기능(replace)
 *       주어진 두 개의 문자열이 편집기능을 단 한번만 이용한 경우(도는 한번도 이용하지 않은 경우)인지를 알아내는 함수를 구현
 */
public class StringApply4 {
    public static void main(String[] args) {
        System.out.println(isOneWay("pal","pale"));
        System.out.println(isOneWay("pale","ple"));
        System.out.println(isOneWay("bale","pale"));

        System.out.println();

        System.out.println(isOneWay("pa","pale"));
        System.out.println(isOneWay("pale","pe"));
        System.out.println(isOneWay("babe","pale"));
    }

    private static boolean isOneWay(String s1, String s2){
        String shortStr, longStr;

        // insert든 remove든 한쪽만 거꾸로 생각하면 같은 기능이기 때문에, 작은 문자열과 큰 문자열간의 차이만 판단이 가능핟.
        if (s1.length() < s2.length()){
            shortStr = s1;
            longStr = s2;
        }else{
            shortStr = s2;
            longStr = s1;
        }
        if (longStr.length() - shortStr.length() > 1) return false;
        boolean flag = false; //서로 다른 문자열이 나왔는지 확인할 변수
        for (int i = 0,j = 0; i < shortStr.length(); i++,j++){
            //편집 기능을 확인해야하기 때문에 정렬하면 안됨
            if (shortStr.charAt(i) != longStr.charAt(j)){ //편집기능이 발생
                if (flag) return false;
                flag = true;
                if (shortStr.length() != longStr.length()){ // 두개의 문자열 길이가 다른 경우
                    j++;
                }
            }
        }
        return true;
    }
}
