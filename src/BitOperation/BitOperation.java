package BitOperation;
/**
 * Integer : 4bytes = 32 bits =  2^32개(-2^32 ~ (2 ^31 -1))
 *          => 음의 정수는 맨 앞자리를 1로 표현하기 때문에 -2^32까지 표현 가능하지만, 양의 정수는 맨 앞자리가 0으로 표현되기에 전원 0인 0의값을 제외하면 2 ^31 -1 개를 표현하는 것이다.
 *          => + - * / 이진법 연산?
 *          => |(OR)연산자 : 둘중 하나라도 1이면 1이다.
 *                  => 전원 0인값과 연산하면 자기자신이 나오고, 전원 1인값과 하면 전원 1이 나온다. 자기자신과 하면 자기자신이 출력된다.
 *             &(AND)연산자 : 둘 보두 1이어야지 1이다.
 *                  => 전원 0인값과 연산하면 전원 0이 값이 나오고, 전원 1인값과 연산하면 자기자신이 나온다. 자기 자신과하면 마찬가지로 자기자신이 나온다.
 *             ~ : 둘이 반대가 됨
 *             ^(XOR)연산자 : 두개가 서로 다른값을 가지고 있으면 1로 셋팅됨
 *                  => 특징 전원 0인 값을 ^하면 원래 값이 나오고, 전원 1인값과 연산하면, 원래값의 ~반대 값이 출력된다. 또한, 자기자신과 하면 전원 0이 출력된다.
 *             <</>>(SHIFT)연산자 : 해당방향으로 1을 각각 N만큼 이동한다. >>이동할때 더이상자리가 없으면 그 값은 사라진다.
 *                  => 사인비트를 고려하는냐 안하는냐로 달라짐
 *                      - 고려 할 경우 : 부호와 상관없이 SHIFT를 logical right shift >>>라고 한다. 계속 반복하면 모든 비트가 0이 된다.
 *                      - 하지 않을 경우 : 부호를 고려해서 하는 shift를 arithmetic right shift >> 라고 한다. shift하는 값이 음수이면 움직이면서 생기는 맨 앞자리 값을 1로 셋팅한다.
 *                                       계속 반복하면 모든 bit가 1로 셋팅된다.
 *                                       즉, <<, >>는 부호만은 꼭 고려한다.
 */
public class BitOperation {
    public static void main(String[] args) {
        System.out.println(getBit(9,3));
        System.out.println(getBit(5,3));
        System.out.println(setBit(5,3));
        System.out.println(clearBit(9,3));
        System.out.println(clearLeftBits(169,3));
        System.out.println(clearRightBits(169,3));
        System.out.println(updateBit(169,3,false));
    }

    public static boolean getBit(int num, int i){ //특정 bit의 값을 찾는 함수
        return (num & 1 << i) != 0;
    }

    public static int setBit(int num, int i){ //특정 bit의 값을 1로 지정
        return num | (1 << i);
    }

    public static int clearBit(int num, int i){ //특정 bit의 값을 0로 지정
        return num & ~(1 << i);
    }

    //해당 인덱스를 시작으로 왼족을 전부 0으로 변경
    public static int clearLeftBits(int num, int i){
        return num & (1 << i) - 1 ;
    }

    //해당 인덱스를 시작으로 오른족을 전부 0으로 변경
    public static int clearRightBits(int num, int i){
        return num & (-1 << (i+1)) ; //모든 bit가 1인 숫자는 -1이다.
    }

    public static int updateBit(int num, int i, boolean val){ //해당 bit를 변경 => 해당 bit를 0으로 변경 후 원하는 값을 변경
        return (num & ~(1 << i)) | ((val? 1 : 0) << i);
    }
}
