package Sort_Search;
/**
 * Quick 정렬 : 정렬이 되어 있지 않은 배열에 임의의 값을 잡아서 오른쪽은 큰값으로, 왼쪽은 작은값으로 재배치되면,
 *             작은쪽에서 또 임의값을 정해서 재배치 하는 정렬 즉, 파티션을 계속 나눠서 재배치하면서 정렬하는 알고리즘,.,,???
 *             => 시간복잡도 : O(nlogn) <= 평균적으로 걸리는 것이고, 더 빨리 걸릴수도 있고, 최악으로는  O(n^2)가 걸릴수도 있다.(기준값을 최소 혹은 최대로 계속 잡을 경우)
 *             => 이진검색트리와 비슷하기에 logn이 걸린다.
 *             => partitioning을 하면서 start end 두개의 포인터로 앞에서 뒤로 가면서 pivot값과 비교해서 start와 end의 위치를 바꾼다
 *             => start가 가면서 큰값을 발견하면 멈추가, end도 가다가 pivot보다 작은 값을 발견하면 start와 end를 변경한다.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[]arr = {3,9,4,7,5,0,1,6,8,2};
        pringArray(arr);
        quickSort(arr);
        pringArray(arr);
    }

    private static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }

    private static void quickSort(int[] arr, int start, int end){
        int part2 = partition(arr,start,end);
        if (start < part2 - 1){ // 기준점 바로 다음에서 시작한다면 왼쪽을 정렬할 필요가 없음
            quickSort(arr,start,part2-1);
        }
        if (part2 < end){ //기준점 보다 end가 작은 경우 오른쪽을 정렬할 필요가 없음
            quickSort(arr,part2,end);
        }
    }

    private static int partition(int[] arr, int start, int end){
        int pivot = arr[(start+end)/2];
        while(start <= end){
            while(arr[start] < pivot) start ++;
            while(arr[end] > pivot) end --;
            if (start <= end){
                swap(arr,start,end);
                start ++;
                end--;
            }
        }
        return start; // 새로 나눌 오른쪽 partition의 start pointer
    }

    private static void swap(int[] arr, int start, int end){
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }

    private static void pringArray(int[] arr){
        for (int data : arr){
            System.out.print(data + ", ");
        }
        System.out.println();
    }
}
