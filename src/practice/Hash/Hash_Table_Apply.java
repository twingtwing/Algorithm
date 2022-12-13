package practice.Hash;

import java.util.HashMap;

/**
 * HashTable 응용
 * 정수값이 담김 배열 안에 숫자중에 2개의 숫자를 더해서 특정한 값이 나오면, 2개 숫자의 인덱스를 반환한다.
 * => 2개의 포인터를 순회하면서, 구할 수 있지만 시간복잡도가 O(n^2)가 걸린다.
 * 1. HashTable을 사용하면, 검색 시간이 O(1)이기 때문에 한개의 포인터만 순회하기 때문에 O(n)의 시간이 걸린다.
 * 2. 미리 HashTable에 넣지 않고, 순회하면서 HashTable에 등록하면서, 값을 찾으면 O(n)보다 적은 시간이 걸릴 수 있다.?
 */
class HashTableApply {
    // 1. HashTable
    public int[] twoSumWithHash(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) hashMap.put(nums[i],i);
        for (int j = 0; j < nums.length; j++) {
            Integer index = hashMap.get(target - nums[j]);
            if (index != null && j != index) return new int[] {j,index};
        }
        throw new IllegalArgumentException();
    }

    // 2. HashTable을 사용하지만, 값을 넣으면서 순회함
    public int[] twoSumWithHash2(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) { //...? 반만 검색 되지 않나,,,?
            if (hashMap.containsKey(target - nums[i])) return new int[] {i,hashMap.get(target - nums[i])};
            hashMap.put(nums[i],i);
        }
        throw new IllegalArgumentException();
    }
}

public class Hash_Table_Apply {
    public static void main(String[] args) {
        int[] nums = {6,4,3,8,7,5,2};
        HashTableApply sol = new HashTableApply();
        int[] result = sol.twoSumWithHash(nums,6);
        System.out.println(result[0] + ", " + result[1]);

        int[] result2 = sol.twoSumWithHash2(nums,6);
        System.out.println(result2[0] + ", " + result2[1]);

    }
}
