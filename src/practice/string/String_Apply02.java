package practice.string;
/**
 * 응용 1. 주어진 문자열의 공백을 %20으로 변환하는 알고리즘
 *        (뒤에는 충분한 공간이 있고, 실제 문자열의 길이 또한 알고있다.)
 * 응용 2. 문자를 삽입하는 insert, 문자를 삭제하는 remove, 문자를 교체하는 replace 기능이 있는 경우
 *        주어진 2개의 문자열이 편집기능을 한번 이하 이용한 경우인지 알아내는 알고리즘
 * 응용 3. 단 한번의 실행으로 두개의 문자열이 회전된 문자열인지 확인할 수 있는 알고리즘
 *         => 회전 : 왼쪽 끝이 오른쪽 끝으로 옮겨지는 행위
 */
public class String_Apply02 {

    // 1. 포인터를 2개 사용 (변환될 index와 변환할 index)
    private static String URLify(String str, int len){return URLify(str.toCharArray(),len);}

    private static String URLify(char [] str, int len){
        int spaces = 0;
        for (int i = 0; i < len; i ++){
            if (str[i] == ' ') spaces++;
        }
        int index = len + spaces * 2 - 1; // 복사할 위치
        for (int p = len - 1; p >= 0; p --){ // 복사될 위치
            if (str[p] == ' ') {
                str[index--] = '0';
                str[index--] = '2';
                str[index--] = '%';
            }else
                str[index--] = str[p];
        }
        return new String(str);
    }

    // 2
    private static boolean isOneWay(String one, String two){
        String shrotStr;
        String longStr;

        if (Math.abs(one.length() - two.length()) > 1) return false;
        if (one.length() > two.length()){
            longStr = one;
            shrotStr = two;
        }else{
            longStr = two;
            shrotStr = one;
        }

        boolean flag = false;
        // 편집기능이 2회 이상 발생하였는지 확인
        for (int i = 0, j = 0; i < shrotStr.length(); i++, j++) {
            if (shrotStr.charAt(i) != longStr.charAt(j)) {
                if (flag) return false;
                if (shrotStr.length() != longStr.length()) j++; //추가 + 삭제 기능이 발생한 위치이므로 건너뜀
                flag = true;
            }
        }

        return true;
    }

    // 3. 앞뒤로 연결하면 중간에 완전한 문자가 생기는 특징을 이용
    //    포함 여부는 contains()할수를 통해 알아냄
    private static boolean isRotation(String one, String two){
        if (one.length() != two.length()) return false;
        return (one + one).contains(two);
    }

    public static void main(String[] args) {
        System.out.println(URLify("Mr John Smith    ",13));

        System.out.println(isOneWay("pal","pale"));
        System.out.println(isOneWay("pale","ple"));
        System.out.println(isOneWay("bale","pale"));

        System.out.println();

        System.out.println(isOneWay("pa","pale"));
        System.out.println(isOneWay("pale","pe"));
        System.out.println(isOneWay("babe","pale"));

        System.out.println(isRotation("string","trings"));
        System.out.println(isRotation("string","ringst"));
        System.out.println(isRotation("string","ingstr"));
        System.out.println(isRotation("string","ngstri"));
        System.out.println(isRotation("string","gstrni"));
    }
}
