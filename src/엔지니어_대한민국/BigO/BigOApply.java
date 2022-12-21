package 엔지니어_대한민국.BigO;
/**
 * 시간복잡도 추측
 */
class BigO{

    // 시간복잡도 : O(a/b) <= b부터 a까지 b만큰씩 증가하면서 돎. 즉 a를 b로 나눈 몫만큼 순회함
    public int bigOQuestion1(int a, int b){
        int count = 0;
        int sum = b;
        while(sum <= a){
            sum += b;
            count++;
        }
        return count;
    }

    // 시간복잡도 : O(sqrt(n))
    public int bigOQuestion2(int n){
        for (int i = 1; i * i <= n; i++){
            if (i*i == n) return i;
        }
        return -1;
    }

    // 만약 이진검색트리가 밸런스가 맞지 않을경우의 시간복잡도 => O(n) 한쪽으로만 치우치면 이진검색트리의 특징을 사용하지 못하므로 전부 순회해야함

    // 이진검색트리가 아닌 이진트리에서 특정한 값을 찾아야하는 경우의 시간 복잡도 => O(n) 모든 노드를 전부 순회해야함

    // 시간복잡도 : O(n^2)
    public int[] bigOQuestion3(int[] ary, int val){
        int[] bigger = new int[ary.length + 1]; //사이즈 1 증가
        for (int i =0; i <ary.length; i++){
            bigger[i] = ary[i];
        }
        bigger[bigger.length-1] = val;
        return bigger;
    }

    public int[] bigOQuestion3Copy(int[] ary){
        int[] copy = new int[0];
        for (int value : ary){
            copy = bigOQuestion3(copy,value);
        }
        return copy;
    }

    // 시간복잡도 : O(logn) <= 돌때마다 데이터가 1/10 감소하기 때문
    public int bigOQuestion4(int n){
        int sum = 0;
        while (n > 0){
            sum += n%10;
            n /= 10;
        }
        return sum;
    }

    // 시간복잡도 : O((b log b + a log b)
    //두개의 배열안에서 중복을 참아내는 함수
    // mergesort와 binarySerach은 너무 많이 쓰이기 때문에 미리 숙지하고 있어야하고, 각각의 시간복잡도도 알고 있어야한다.
    // 각각의 시간복잡도 o(logn)이다.?
//    public int bigOQuestion5(int[] a, int[] b){
//        mergesort(b);
//        int intersect = 0;
//        for (int x : a){
//            if (binarySerach(b,x) >= o){
//                intersect++;
//            }
//        }
//        return intersect;
//    }

}

public class BigOApply {
    public static void main(String[] args) {

    }
}
