package 자바로_배우는_쉬운_자료구조.Sort;

class QuickSort{
    int i = 0;
    public int partition(int ary[], int begin, int end){
        int pivot, left, right;
        left = begin;
        right = end;
        pivot = (begin+end)/2;
        while (left < right){
             while ((ary[left] <= ary[pivot]) && (left <= right)) left++;
             while ((ary[right] > ary[pivot]) && (left <= right)) right--;
             if (left < right) swap(ary,left,right);
             if (left == pivot) return right;
        }
        swap(ary,pivot,right);
        return right;
    }

    public void sort(int ary[]){
        sort(ary,0,ary.length-1);
    }

    public void sort(int ary[],int begin, int end){
        if (begin < end){
            int part;
            part = partition(ary,begin,end);
            sort(ary,begin,part-1);
            sort(ary,part+1,end);
        }
    }

    public void swap(int ary [], int i, int j){
        int temp = ary[i];
        ary[i] = ary[j];
        ary[j] = temp;
    }

    public void printSort(int ary []){
        for (int i = 0; i<ary.length; i++){
            System.out.print(ary[i] + " ");
        }
        System.out.println();
    }
}

public class Quick_Sort {
    public static void main(String[] args) {
        int ary[] = {69,10,30,16,8,31,22,2};
        QuickSort sort = new QuickSort();
        sort.sort(ary);
        sort.printSort(ary);

    }
}
