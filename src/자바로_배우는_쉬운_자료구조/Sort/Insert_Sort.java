package 자바로_배우는_쉬운_자료구조.Sort;

class InsertSort{
    public void sort(int ary[]){
        if (ary.length < 2) return;
        for (int i = 1; i < ary.length; i++){
            int now = ary[i]; // for구문을 돌면서 배열의 값이 변하기 때문에 먼저 값을 할당해둠
            for (int j = i -1; j >= 0; j--){
                if (ary[j] > now) swap(ary,j);
                // 배열의 위치는 앞자리로 바뀌고 있기 때문에 i의 index값은 필요 없어짐
            }
        }
    }

    public void swap(int ary [], int j){
        int temp = ary[j];
        ary[j] = ary[j+1];
        ary[j+1] = temp;
    }

    public void printSort(int ary []){
        for (int i = 0; i<ary.length; i++){
            System.out.print(ary[i] + " ");
        }
        System.out.println();
    }
}

public class Insert_Sort {
    public static void main(String[] args) {
        int ary[] = {10,69,30,16,8,31,22,2};
        InsertSort sort = new InsertSort();
        sort.sort(ary);
        sort.printSort(ary);

    }
}
