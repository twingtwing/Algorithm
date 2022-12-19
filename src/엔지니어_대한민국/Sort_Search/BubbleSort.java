package 엔지니어_대한민국.Sort_Search;
/**
 * BubbleSort : 2개씩 바로 앞이랑 비교하면서 정렬하고,
 *              마지막 인덱스를 제외하고 한번 더 정렬 행렬 처음까지 정렬할때까지 반복한다.
 *              => 시간복잡도 O(n^2)
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] ary = {3,9,4,7,5,0,1,6,8,2};
        pringArray(ary);
        bubbleSort(ary);
        pringArray(ary);
    }
    
    private static void bubbleSort(int[] ary){
        bubbleSort(ary,ary.length - 1);
    }

    private static void bubbleSort(int[] ary, int end){
        if (end > 0){
            for (int i = 1; i <= end; i++){
                if (ary[i-1] > ary[i]){
                    swap(ary,i-1,i);
                }
            }
            bubbleSort(ary,end-1);
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
