package Arrays_Strings;
/**
 * 응용 : 주어진 문자열이 회문(palindrorne)을 만들 수 있는 문자열의 치환(permutation)인지를 확인하는 알고리즘 구현
 *       - 회문의 조건 : 홀수개인 문자가 1개 이하여야 함
 */
public class StringArrayApply {
    public static void main(String[] args) {
        System.out.println(isPermutationOfPalindrorne("aa bb cc dd"));
        System.out.println(isPermutationOfPalindrorne("aa bb cc dd e"));
        System.out.println(isPermutationOfPalindrorne("aa bb cc dd e fff"));

        System.out.println();

        System.out.println(isPermutationOfPalindrorne2("aa bb cc dd"));
        System.out.println(isPermutationOfPalindrorne2("aa bb cc dd e"));
        System.out.println(isPermutationOfPalindrorne2("aa bb cc dd e fff"));

        System.out.println();

        System.out.println(isPermutationOfPalindrorne3("aa bb cc dd"));
        System.out.println(isPermutationOfPalindrorne3("aa bb cc dd e"));
        System.out.println(isPermutationOfPalindrorne3("aa bb cc dd e fff"));

    }

    // 처음부터 끝까지 돌아야하기에 O(n)의 시간이 걸림
    private static boolean isPermutationOfPalindrorne(String s){
        int [] table = buildCharFrequencyTable(s);
        return checkMaxOneOdd(table);
    }

    private static int[] buildCharFrequencyTable(String s){ //문자 갯수를 세어서 문자에 저장하는 함수
        int[] table = new int[Character.getNumericValue('z')
                - Character.getNumericValue('a')+ 1];  // z에서 a까지 아스키값을 빼고 거기서 공백을 포함한 size
        for (char c : s.toCharArray()){
            int x = getCharNumber(c); //배열 방 번호를 가지고옴
            if (x != -1){ //다른 특수문자 제외
                table[x] ++;
            }
        }
        return table;
    }

    private static int getCharNumber(char c){
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if ( a <= val && val <= z){
            return val - a;
        }
        return -1;
    }

    private static boolean checkMaxOneOdd(int[] table){
        boolean founded = false;
        for (int count : table){
            if (count %2 == 1){
                if(!founded){
                    founded = true;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    // 갯수를 세면서 홀수가 몇개인지를 동시에 확인하면 O(n)의 시간이 적게 걸릴 가능성이 있다.
    private static boolean isPermutationOfPalindrorne2(String s){
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z')
                - Character.getNumericValue('a')+ 1];
        for (char c : s.toCharArray()){ // 다..도는거 같은데... 아닌가?
            int x = getCharNumber(c); //배열 방 번호를 가지고옴
            if (x != -1){ //다른 특수문자 제외
                table[x] ++;
                if (table[x] %2 ==1){
                    countOdd++;
                }else{
                    countOdd--;
                }
            }
        }
        return countOdd <=  1;
    }

    // Bit Operation : 정수를 이진수 배열방으로 생각하고(짝수는 0,홀수는 1) 마지막에 1이 몇개인지 확인
    //                 짝수개를 0으로 만들수잇는것은 &연산다를 통해 가능 여기서 홀수를 그대로 1로 만들려면 |연산자를 사용한다.
    //                 만약에 겹치는것이 있을경우 짝수로 만들어야하므로, 기존의값과 새로운 값을 비교해야하므로 기존을 ~한 후에 &을 해야한다.
    //                  마지막에 1이 몇개인지를 확인할려면 1을 -연산자를 하고, 다시 그 값으로 &연산을 해서 값이 0이 아니면 홀수개가 1이상이다.
    private static boolean isPermutationOfPalindrorne3(String s){
        int bitVector = createBitVector(s);
        return bitVector ==0 || checkExactlyOneBitSet(bitVector); //모두 짝수개이거나 한개만 홀수개
    }

    private static int createBitVector(String s){
        int bitVector = 0;
        for (char c: s.toCharArray()){
            int x = getCharNumber(c);
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }

    private static int toggle(int bitVector, int index){
        if (index < 0 ) return bitVector;
        int mask =  1 << index;
        if ((bitVector & mask) == 0){
            bitVector |= mask;
        }else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    private static boolean checkExactlyOneBitSet (int bitVector){
        return (bitVector &(bitVector-1)) == 0;
    }
}
