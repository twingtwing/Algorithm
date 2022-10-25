package 엔지니어_대한민국.Arrays_Strings;

import java.util.HashMap;

/**
 * 응용 : 정수값이 담긴 배열안에 있는 숫자중에 두개의 숫자를 더해서 특정한 값이 나오면,
 *       두개의 숫자의 인덱스를 반환하도록한다.
 *       단, 더했을 경우 고유한 숫자만 출력이 되도록 배열이 구성이 되어있음)
 *       => 두개의 포인터로 순회하면서 구할 수 있지만, 이러면 시간복잡도가 o(n^2)의 시간이 걸리기 때문에 비효율적
 *       => HashTable을 이용해서 key에는 값, value에는 인덱스값을 저장
 *          HashTable은 검색 시간이 O(1)이기때문에 조회시간이 용이함
 *          이러면 포인터 한개를 이용해서 한번만 순회하면 되므로 O(n)의 시간이 걸린다.
 *       => 미리 HashTable넣지 말고, 순회하면서 HashTable에 등록함
 *          넣으면서 값을 찾기 때문에 꼭 2번째 숫자까지 가야된다?
 */
class Solution{
    public int[] twoSum(int[] nums, int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) map.put(nums[i],i);
        for (int j=0; j<nums.length; j++){
            Integer integer = map.get(target - nums[j]); // index위치도 같이 변경됨
            if (integer != null && j != integer) return new int[]{j,integer}; //인덱스값을 돌려줌
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSum2(int[] nums, int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++){
            if (map.containsKey(target - nums[i])) return new int[]{map.get(target - nums[i]),i};
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

public class ArrayApply {
    public static void main(String[] args) {
        int[] nums = {6,4,3,8,7,5,2};
        Solution sol = new Solution();
        int[] result = sol.twoSum(nums,6);
        System.out.println(result[0] + ", " + result[1]);

        int[] result2 = sol.twoSum2(nums,6);
        System.out.println(result2[0] + ", " + result2[1]);
    }
}
