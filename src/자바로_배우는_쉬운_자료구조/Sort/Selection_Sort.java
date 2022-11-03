package 자바로_배우는_쉬운_자료구조.Sort;

class SelectSort{
    public void sort(int ary []){
        for (int i = 0; i<ary.length-1; i++){
            int min = i;
            for (int j = i+1; j<ary.length; j++){
                if (ary[j] < ary[min]) min = j;
            }
            if(i != min)swap(ary,i, min);
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

public class Selection_Sort {
    public static void main(String[] args) {
        int ary[] = {10,69,30,16,8,31,22,2};
        SelectSort sort = new SelectSort();
        sort.sort(ary);
        sort.printSort(ary);

    }
}
