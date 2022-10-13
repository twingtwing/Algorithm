package BigO;
/**
 * Fibonacci Numbers 구현..?
 */
class FibonacciNumber{
    public int f(int n, int[] r){
        if (n <= 0) return 0;
        else if (n==1) return r[n] = 1;
        else if (r[n] > 0) return r[n]; // 한번 호출한 데이터는 다시 하지 않음
                                        // 이 로직이 있으면 o(n)의 시간이 걸리지만, 없으면 o(2^n)의 시간이 걸림
        return r[n] = f(n-1, r) + f(n - 2, r);
    }
}

public class Fibonacci {
    public static void main(String[] args) {

    }
}
