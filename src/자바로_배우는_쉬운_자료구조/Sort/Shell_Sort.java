package 자바로_배우는_쉬운_자료구조.Sort;

class ShellSort{
    public void intervalSort(int ary[], int begin, int end, int interval){
        int item,j;
        for (int i = begin + interval; i <= end; i=i+interval){
            item = ary[i];
            for (j = i-interval; j >= begin && item < ary[j]; j -= interval)
                ary[j+interval] = ary[j];
            ary[j+interval] = item;
        }
    }

    public void sort(int ary[]){
        int interval = ary.length/2;
        while (interval >= 1){
            for (int i =0; i < interval; i++)
                intervalSort(ary,i,ary.length-1,interval);
            interval /= 2;
        }
    }

    public void printSort(int ary []){
        for (int i = 0; i<ary.length; i++){
            System.out.print(ary[i] + " ");
        }
        System.out.println();
    }

}

public class Shell_Sort {
    public static void main(String[] args) {
        int ary[] = {10,69,30,16,8,31,22,2};
        ShellSort sort = new ShellSort();
        sort.sort(ary);
        sort.printSort(ary);
    }
}
