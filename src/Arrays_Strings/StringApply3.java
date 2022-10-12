package Arrays_Strings;
/**
 * 응용 : 주어진 문자열의 공백을 %20으로 변환하는 함수를 구현하시오.
 *       문자열의 맨 끝에는 변환에 필요한 층분하 공백이 있고, 실제 문자열의 사이즈를 알고 있음
 *       => 뒤에서 부터 복사하면 끝까지 갈 필요가 없음
 *       => 이때, pointer와 index를 두개 가지면서 이동함.
 *       => 복사할 위치 pointer / 복사될 위치 index
 */
public class StringApply3 {
    public static void main(String[] args) {
        System.out.println(URLify("Mr John Smith    ",13));
    }

    private static String URLify(String str, int len){
        return URLify(str.toCharArray(), len);
    }

    private static String URLify(char[] str, int len){
        int spaces = 0;
        for (int i = 0; i < len; i++){
            if (str[i] == ' ') spaces ++;
        }
        int index = len + spaces * 2 - 1; //index (공백 + 2, 0부터 시작 -1)
        for (int p = len - 1; p >= 0; p--){ //pointer
            if (str[p] == ' '){
                str[index--] = '0'; //치환
                str[index--] = '2';
                str[index--] = '%';
            }else{
                str[index--] = str[p]; // 복사
            }
        }
        return new String(str);
    }


}
