package practice.dynamic_Programming;
/**
 * Dynamic Program
 * 1. 최소 비용으로 계단 오르기
 * 계단을 오를때마다 지정된 비용을 지불애햐하고, 계단은 1 ~ 2 칸만 가능하다. 계단 시작점은 0 혹은 1 이다.
 * !! key point : 뒤에서부터 시작하여 값을 구한다.
 * 2. 로봇이 grid 왼쪽 상단에 위치하고 있고, 아래 혹은 오른똑으로 밖에 움직이지 못한다.
 * 좌측 상단에서 우측하단으로 이동하는 알고리즘
 * 최소비용 계단과 마찬가지로 뒤에서부터 시작한다.
 */
public class dynamic_Programming_Apply {
    // 응용 1
    public static int minCostStaris(int[] stairCost) {
        int one = 0;
        int two = 0;
        int current;
        for (int i = stairCost.length - 1; i >= 0; i--) {
            // i위치에서 1 ~ 2 칸전에서 내려온 비용중에 최소 비용을 찾음
            current = stairCost[i] + Math.min(one,two);
            two = one; //현재 위치에서 1칸 전(다음 위치와 2칸 차이)
            one = current; // 현재위치(다음위치와 1칸 차이)
        }
        // 시작 위치를 0 혹은 1로 할지 구함
        return Math.min(one,two);
    }

    // 응용 2


    public static void main(String[] args) {
        int[] cost = new int[] {1,2,3,4,5,6,7};
        int result = minCostStaris(cost);
        System.out.println(result);

    }
}
