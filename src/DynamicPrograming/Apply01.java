package DynamicPrograming;
/**
 * Dynamic Program을 이용한 응용 1,
 * 최소 비용으로 계단오르기
 * 계단에서 i번째 계단을 올라갈려면 양수값을 가진 cost[i]가 지불되어야함(계단 배열마다 지불하는 비용이 다름)
 * 또한, 계단 오르기는 한칸 혹은 두칸만 가능하고, 계단 시작점을 0 혹은 1로 선택할 수 있다.
 * => 뒤에서 시작해서 경우의 수를 구한다.
 */
public class Apply01 {
    public static void main(String[] args) {
        int[] cost = new int[] {1,2,3,4,5,6,7};
        int result = minCostClimbingStairs(cost);
        System.out.println(result);
    }

    public static int minCostClimbingStairs(int[] cost){
        int case1 = 0, case2 = 0, current;
        for (int i = cost.length - 1; i >= 0; --i){
            current = cost[i] + Math.min(case1,case2);
            case2 = case1;
            case1 = current;
        }
        return Math.min(case1,case2);
    }
}
