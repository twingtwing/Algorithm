package practice.sort;
/**
 * Quick Sort
 * 기준값을 통해 파티션을 나누고, 기준값을 이용해 대소비교를 하여 재배치한다.
 * => 시간복잡도 O(nlogn) : 평균값이고, 더 걸리 수 도있음 최댓값 O(n^2)
 */
public class Quick_Sort {
    private static void quickSort(int [] ary) {quickSort(ary, 0, ary.length -1);}

    private static void quickSort(int[] ary, int start, int end) {
        // 정렬 후 기준값 out
        int point = partition(ary,start, end);
        // 왼쪽이 1개 밖에 없을 경우(start = point -1), 왼쪽 정렬할 필요가 없음
        if (start < point - 1) quickSort(ary, start, point-1);
        // 오른쪽이 1개 밖에 없을 경우(end = point), 오른쪽 정렬할 필요가 없음
        if (point < end) quickSort(ary, point, end);
    }

    private static int partition(int[] ary, int start, int end) {
        int pivot = (start + end) / 2;
        // start, end 2개의 포인터가 앞, 뒤 양쪽에서 오면서 값을 비교하고
        // 양쪽 다 변경해야하는 인덱스를 도착했을 때, 위치를 변경한다.
        while(start <= end){
            // 기준값보다 큰 값을 찾을 때까지 인덱스 변경
            while(ary[start] < ary[pivot]) start ++ ;
            // 기준값보다 작은 값을 찾을 때까지 인덱스 변경
            while (ary[end] > ary[pivot]) end --;

            if (start <= end){
                swap(ary, start, end);
                start ++;
                end --;
            }

        }
        // 마지막에는 start = end = pivot 위치에 도달함 ?
        return pivot;
    }

    private static void swap(int[] ary, int prev, int next) {
        int tmp = ary[prev];
        ary[prev] = ary[next];
        ary[next] = tmp;
    }

    private static void pringArray(int[] arr){
        for (int data : arr){
            System.out.print(data + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[]arr = {3,9,4,7,5,0,1,6,8,2};
        pringArray(arr);
        quickSort(arr);
        pringArray(arr);
    }
}
