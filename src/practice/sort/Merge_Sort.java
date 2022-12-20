package practice.sort;
/**
 * Merge Sort
 * n개로 나눈어 각각 서로 정렬하고 병합을 반복하면서 한개의 배열로 정렬될때까지 배열한다.
 * 시간복잡도 O(nlogn)이 걸린다. n개 만큼 logN번 돌리기 때문이다.
 */
public class Merge_Sort {
    public static void mergeSort(int[] ary) { mergeSort(ary,  new int[ary.length], 0, ary.length - 1);}

    private static void mergeSort(int[] ary, int[] tmp, int start, int end) {
        if(start >= end) return;
        int mid = (start + end) / 2;
        // divide
        mergeSort(ary, tmp, start, mid);
        mergeSort(ary, tmp, mid + 1, end);
        // merge
        merge(ary,tmp,start,mid,end);
    }

    private static void merge(int[] ary, int[] tmp, int start, int mid, int end) {
        // 임시저장소에 copy
        for (int i = 0; i < ary.length; i++) tmp[i] = ary[i];

        // 2개의 pointer가 동시에 움직임
        int one = start; // pointer
        int two = mid + 1; // pointer
        int index = start; // 원본 index

        //merge와 동시에 sort
        while (one <= mid && two <= end) { // 배열 2개중 한개가 끝에 도달하면 끝난다.
            if (tmp[one] <= tmp[two]) ary[index++] = tmp[one++];
            else ary[index++] = tmp[two++];
        }

        //앞의 배열이 남았을 경우
        for (int i = 0; i <= mid - one; i++) ary[index + i]  = tmp[one + i];

        // 뒤의 배열이 남았을 경우는 이미 ary 배열에 뒷쪽에 남아있음

    }

    private static void pringArray(int[] arr){
        for (int data : arr){
            System.out.print(data + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] ary = {3,9,4,7,5,0,1,6,8,2};
        pringArray(ary);
        mergeSort(ary);
        pringArray(ary);

    }
}
