package 자바로_배우는_쉬운_자료구조.Sort;

class MergeSort{
    private int sorted[] = new int[30];

    public void merge(int ary[], int m, int middle, int n){
        int size= ary.length;
        int i = m;
        int j = middle + 1;
        int k = m;
        while (i<= middle && j <= n){
            if (ary[i] <= ary[j]) sorted[k] = ary[i++];
            else sorted[k] = ary[j++];
            k++;
        }
        if (i>middle){
            for (int t =j; t <= n; t++,k++)
                sorted[k] = ary[t];
        }else{
            for (int t = i; t <= middle; t++, k++)
                sorted[k] = ary[t];
        }

        for (int t = m; t <= n; t++)
            ary[t] = sorted[t];
    }

    public void sort(int a[], int m, int n){
        int middle;
        if(m<n){
            middle = (m+n)/2;
            sort(a,m,middle);
            sort(a,middle+1,n);
            merge(a,m,middle,n);
        }
    }

    public void printSort(int ary []){
        for (int i = 0; i<ary.length; i++){
            System.out.print(ary[i] + " ");
        }
        System.out.println();
    }
}

public class Merge_Sort {
    public static void main(String[] args) {
        int ary[] = {10,69,30,16,8,31,22,2};
        MergeSort sort = new MergeSort();
        sort.sort(ary,0,ary.length-1);
        sort.printSort(ary);
    }
}
