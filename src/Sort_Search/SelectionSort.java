package Sort_Search;
/**
 * SelectionSort : 배열을 돌면서 최솟값을 맨 앞 index와 위치와 계속 변경한다. 그 후, 맨 앞 index를 제외하고 순회하고, 마지막 index차례가 올때까지 반복한다.
 *                 => 시간 복잡도가 O(n^2)가 걸린다.
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] ary = {3,9,4,7,5,0,1,6,8,2};
        pringArray(ary);
        selectionSort(ary,0);
        pringArray(ary);
    }

    private static void selectionSort(int[] ary,int start){
        if (start < ary.length - 1){
            int min_index = start;
            for (int i = start; i < ary.length; i++){
                if (ary[i] < ary[min_index]) min_index = i;
            }
            swap(ary,start,min_index);
            selectionSort(ary,start+1);
        }
    }

    private static void swap(int[] ary, int source, int target){
        int tmp = ary[source];
        ary[source] = ary[target];
        ary[target] = tmp;
    }

    private static void pringArray(int[] arr){
        for (int data : arr){
            System.out.print(data + ", ");
        }
        System.out.println();
    }
}
