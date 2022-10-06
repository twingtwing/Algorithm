package Arrays_Strings;

class ArrayList{
    private Object[] ary;
    private int size;
    private int index;

    public ArrayList(){
        this.size = 1;
        this.ary = new Object[this.size];
        this.index = 0;
    }

    public void add(Object obj){
        if (this.index == this.size -1){ // 방이 다 차있는지 확인
            doubling();
        }
        ary[this.index] = obj;
        this.index ++;
    }
    
    public void doubling(){
        this.size = this.size * 2;
        Object[] newAry = new Object[this.size];
        for (int i = 0; i < ary.length; i++){
            newAry[i] = ary[i];
        }
        this.ary = newAry;
    }

    public Object get(int i) throws Exception{
        if(i>this.index - 1){
            throw new Exception("ArrayIndexOutOfBound");
        }else if(i<0){
            throw new Exception("Negative Value");
        }
        return this.ary[i];
    }

    public void remove(int i) throws Exception{
        if(i>this.index - 1){
            throw new Exception("ArrayIndexOutOfBound");
        }else if(i<0){
            throw new Exception("Negative Value");
        }
        for (int j = i; j < ary.length -1; j++){ //한칸씩 앞으로 이동
            ary[j] = ary[j+1];
        }
    }
}

public class ArrayLists {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");


    }
}
