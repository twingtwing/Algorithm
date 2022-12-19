package practice.sort;
/**
 * Bubble Sort
 *  2개씩 바로 서로 비교하면서 정렬하면서 최댓값을 마지막 위치로 이동한다.
 *  이러한 과정을 반복한다.
 *  => 시간복잡도 O(n^2)
 */
public class Bubble_Sort {
    private static void bubbleSort(int [] ary){bubbleSort(ary, ary.length - 1);}

    private static void bubbleSort(int[] ary, int end) {
        if (end == 0) return;
        for (int i = 0; i < end; i++) if (ary[i] > ary[i + 1]) swap(ary, i, i+1);
        bubbleSort(ary, end - 1);
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
        int[] ary = {3,9,4,7,5,0,1,6,8,2};
        pringArray(ary);
        bubbleSort(ary);
        pringArray(ary);
    }
}
