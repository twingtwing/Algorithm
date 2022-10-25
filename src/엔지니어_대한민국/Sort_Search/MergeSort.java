package 엔지니어_대한민국.Sort_Search;
/**
 * MergeSort : 2개씩 나누어서 서로 정렬하고 두 배열을 병합하면서 정렬하고 다시 두 배열을 병합하면서 정렬해서 한개의 배열이 될때까지 반복한다.
 *             시간복잡도는 O(nlogn)이 걸린다. n개 만큼 logn번 돌기때문이다.
 *             => 별도의 저장공간이 필요하기 때문에, 공간이 사용하지 못할 경우 QuickSort를 사용한다.
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] ary = {3,9,4,7,5,0,1,6,8,2};
        pringArray(ary);
        mergSort(ary);
        pringArray(ary);
    }

    private static void mergSort(int[] ary){
        int[] tmp = new int[ary.length]; // 임시 저장소
        mergSort(ary, tmp, 0, ary.length - 1);
    }

    private static void mergSort(int[] ary, int[] tmp, int start, int end){
        if (start < end){
            int mid = (start + end) / 2;
            mergSort(ary,tmp,start,mid);
            mergSort(ary,tmp,mid + 1,end);
            merge(ary,tmp,start,mid,end);
        }
    }

    private static void merge(int[] ary, int[] tmp, int start, int mid, int end){
        for (int i = start; i<= end; i++){
            tmp[i] = ary[i];
        }
        int part1 = start;
        int part2 = mid + 1;
        int index = start;
        // merge하면서 정렬
        while(part1 <= mid && part2 <= end){ // 두 배열둘 중 하나가 끝날때까지 돌린다.
            if (tmp[part1] <= tmp[part2]){
                ary[index] = tmp[part1];
                part1 ++;
            }else{
                ary[index] = tmp[part2];
                part2 ++;
            }
            index++;
        }
        // 앞쪽 배열이 남을 경우 (뒷쪽 배열은 이미 ary의 뒷쪽에 있기 때문에 신경쓸 필요 없음)
        for (int i = 0; i<= mid - part1; i++){
            ary[index + i] = tmp[part1 + i];
        }
    }

    private static void pringArray(int[] arr){
        for (int data : arr){
            System.out.print(data + ", ");
        }
        System.out.println();
    }
}
