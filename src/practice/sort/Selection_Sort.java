package practice.sort;
/**
 * 선택 정렬
 * 배열을 순회하면서 최솟값 혹은 최댓값을 찾아서 위치를 교환하는 과정을 반복한다.
 * => 시간복잡도 O(n^2)
 */
public class Selection_Sort {

    private static void selectionSort(int [] ary, int index){
        if (index >= ary.length - 1) return;
        int min = index;
        for (int i = index + 1; i < ary.length; i++)  if (ary[min] > ary[i]) min = i; // 가장 작은 최솟값을 찾음
        swap(ary, index, min);
        selectionSort(ary, index + 1);
    }

    private static void swap(int [] ary, int prev, int next) {
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
        selectionSort(ary,0);
        pringArray(ary);
    }
}
