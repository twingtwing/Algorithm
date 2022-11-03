package 자바로_배우는_쉬운_자료구조.Sort;

class BubbleSort{
    public void sort(int ary[]){
        for (int i = ary.length -1; i > 0; i--){
            for (int j = 0; j < i; j++){
                if (ary[j] > ary[j+1])swap(ary,j, j+1);
            }
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

public class Bubble_Sort {
    public static void main(String[] args) {
        int ary[] = {10,69,30,16,8,31,22,2};
        BubbleSort sort = new BubbleSort();
        sort.sort(ary);
        sort.printSort(ary);
    }
}
