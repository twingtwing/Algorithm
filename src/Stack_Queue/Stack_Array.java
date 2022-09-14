package Stack_Queue;

import java.util.EmptyStackException;

/**
 * Stack : LIFO(후입선출 방식) => 배열(Array) 로 구현
 * 1. 쉬운방법 : 고정길이 스택
 * 2. 어려운 방법 : 유동길이 스택
 */
class FullStackException extends Exception{
    public FullStackException(){
        super();
    }
    public FullStackException(String msg){
        super(msg);
    }
}

class Stack_Easy{
    private int numOfStacks = 3; //배열을 3등분하여 해당 영역만 사용함
    /**
     * 데이터 접근시에는 stack의 번호와 stack의 크기를 곱하여 스택의 시작점을 구하고, 
     * 여기에 데이터 크기를 더해서 마지막 데이터에 접급함.
     * 그렇기에 pop,push과정에서 데이터 크기를 업데이트하여 top의 위치가 어디인지 알아애힘
     */
    private int stackSize; //stack의 사이즈(스택들의 사이즈는 모두 균등함)
    private int[] values; //실제 데이터
    private int[] sizes; //실제 데이터 사이즈

    public Stack_Easy(int stackSize){
        this.stackSize = stackSize;
        this.sizes = new int[numOfStacks]; //각 스택의 갯수만큼 데이터 사이즈를 저장할 배열을 생성(사이즈가 3인 배열이 만들어짐)
        this.values = new int[numOfStacks * stackSize]; //실제 데이터를 저장할 공간을 생성
    }

    public boolean isEmpty(int stackNum){
        return sizes[stackNum] == 0; //해당 스택 번호에 해당하는 size배열값이 0이면 빈 스택
    }

    public boolean isFull(int stackNum){
        return sizes[stackNum]  == stackSize; //해당 스택 번호에 해당하는 size배열값이 stackSize와 일치하면 full stack
    }


    public int getTopIndex(int stackNum){//해당 스택번호에 해당하는 스택의 top이 존재하는 index값을 가지고옴
        int offset = stackSize * stackNum;
        int size = sizes[stackNum];
        return offset + size - 1; //배열은 0부터 시작하기 때문
    }

    public void push(int stackNum, int data) throws FullStackException{
        if(isFull(stackNum)){
            throw new FullStackException();
        }
        values[getTopIndex(stackNum) + 1] = data;
        sizes[stackNum]++; //size값을 계속 업뎃해주어야함
    }

    public int pop(int stackNum){
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        int top = getTopIndex(stackNum);
        int data = values[top];
        values[top] = 0;   // top값 삭제(type : integer이므로 0으로 할당)
        sizes[stackNum]--; //size값을 계속 업뎃해주어야함
        return data;
    }

    public int peek(int stackNum){
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        return values[getTopIndex(stackNum)];
    }
}

class Stack_Hard{
    /**
     * 배열이기 때문에 stack의 공간이 한정적인것은 여전하나, 다른 stack의 공간이 남을 경우 유동적으로 크기를 변경한다.
     * 일직선이 아닌 하나의 원으로 생각하면서 공간을 빌려준다. 실제로 원으로 되어 있지만, 그렇게 인식하고 여유공간을 불러준다.
     * 그러나, 실제로는 일직선이기때문에 shift할때, 배열 인덱스 값이 마이너스가 나오는 일이 발생할 수 있다.
     * 이를 해결해주는 방법이 Modulo의 Wrapping Around이다.
     * Mod() 죽 %로 연산하면, 연산하는 값을 벚어나지 않는다. 이때, 음의 정수는 + 배열전체 길이를 더해준다.
     * 그러면 -1값이 들어와도, 연산하는 값을 벚어나지 않고, 배열 마지막 값을 가지고 온다.
     * 그러나 이때, 양의정수에 배열 전체 길이를 마지막에 더하면 값을 벚어나므로 마지막에 한번더 %배열 전체길이를 해준다.
     */
    private class StackInfo{
        public int start, dataSize, stackSize;

        public StackInfo(int start, int stackSize){
            this.start = start;
            this.stackSize = stackSize;
            this.dataSize = 0;
        }

        public boolean isWithinStack(int index){ //임의의 배열방이 해당 스택영역이 존재하는지 check
            if(index < 0 || index >= values.length){
                return false;
            }
            int virtualIndex = index < start? index + values.length : index; // 스택이 한바퀴돌아서 last가 start보다 인덱스 값이 작아진경(원으로 취급했기 때문)
            int end = start + stackSize;
            return start <= virtualIndex && virtualIndex < end; //시작과 끝지점에 있는지
        }

        public int getLastStackIndex(){ // 스택의 끝지점의 index
            return adjustIndex(start + stackSize -1 ); //배열은 0부터 시작하기 때문
        }

        public int getLastDataIndex(){ // 해당스택의 top index
            return adjustIndex(start + dataSize -1 ); //배열은 0부터 시작하기 때문
        }

        public int getNewDataIndex(){
            return adjustIndex(getLastDataIndex()+1);
        }

        public boolean isFull(){
            return dataSize == stackSize;
        }

        public boolean isEmpty(){
            return dataSize == 0;
        }

    }

    private StackInfo[] info;
    private int[] values;

    public Stack_Hard(int numOfStacks, int defaultSize){
        info = new StackInfo[numOfStacks];
        for(int i = 0; i< numOfStacks; i++){
            info[i] = new StackInfo(defaultSize* i,defaultSize);
        }
        values = new int[numOfStacks * defaultSize];
    }

    private void expend(int stackNum){// 자리가 없을때 shift호출하고, 마무리 작업을 하는 작업
        int nextStack = (stackNum + 1) % info.length; //현재 스택번호를 가져와 쉬프트해야할 다음스택을 찾음
        shift(nextStack); //shift함수 호출
        info[stackNum].stackSize++; //shift하였기에 현재 스택의 길이를 업뎃 해주어야함
    }

    private void shift(int stackNum){
        StackInfo stack = info[stackNum]; //해당 stack 정보를 가지고옴
        if(stack.dataSize >= stack.stackSize){ //shfit할 스택도 full상태이면 재귀호출한다.
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack);
            stack.stackSize++;
        }
        int index = stack.getLastStackIndex(); //자리가 남는 스택을 갖고오면, 해당 스택의 top의 index를 가지고옴
        while (stack.isWithinStack(index)){  //데이터를 뒤로 미루다가, 스택의영역을 벗어나면 멈춘다.
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }
        values[stack.start] = 0; //한칸씩 뒤로 미르면, 가장 앞의 인덱스는 비워지므로 따로, 초기화 해주어ㅑ함
        stack.start = nextIndex(stack.start);
        stack.stackSize--;
    }

    public int numberOfElements(){ //전체 배열에 데이터가 얼마나쌓였는지 확인하는 함수
        int totalDataSize = 0;
        for(StackInfo si : info){
            totalDataSize += si.dataSize;
        }
        return totalDataSize;
    }

    public boolean allStackAreFull(){ //스택이 전체에 다쌓였는지 확인하는 함수
        return numberOfElements() == values.length;
    }

    private int adjustIndex(int index){ //배열 크기를 벗어나느 가상인덱스
        int max = values.length;
        return ((index % max) + max) % max;
    }

    private int nextIndex(int index){
        return adjustIndex(index + 1);
    }

    private int previousIndex(int index){
        return adjustIndex(index - 1);
    }

    public void push(int stackNum, int value) throws FullStackException{
        if(allStackAreFull()){
            throw new FullStackException();
        }
        StackInfo stack = info[stackNum];
        if(stack.isFull()){
            expend(stackNum);
        }
        values[stack.getNewDataIndex()] = value;
        stack.dataSize ++;
    }

    public int pop(int stackNum) throws Exception{
        StackInfo stack = info[stackNum];
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        int top = stack.getLastDataIndex();
        int data = values[top];
        values[top] = 0;
        stack.dataSize--;
        return data;
    }

    public int peek(int stackNum){
        StackInfo stack = info[stackNum];
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return values[stack.getLastDataIndex()];
    }

}

public class Stack_Array {
    public static void main(String[] args){
//        Stack_Easy se = new Stack_Easy(5);
//        try {
//            se.push(0,1);
//            se.push(0,2);
//            se.push(0,3);
//            se.push(0,4);
//            se.push(0,5);
//
//            se.push(1,11);
//            se.push(1,12);
//            se.push(1,13);
//            se.push(1,14);
//            se.push(1,15);
//
//        }catch (FullStackException e){
//            System.out.println("It's full");
//        }
//        
//        try {
//            System.out.println(se.pop(0));
//            System.out.println(se.pop(0));
//            System.out.println(se.peek(0));
//            System.out.println(se.pop(0));
//            System.out.println(se.isEmpty(0));
//            System.out.println(se.pop(0));
//            System.out.println(se.pop(0));
//            System.out.println(se.isEmpty(0));
//
//            System.out.println(se.pop(1));
//            System.out.println(se.pop(1));
//            System.out.println(se.peek(1));
//            System.out.println(se.pop(1));
//            System.out.println(se.isEmpty(1));
//            System.out.println(se.pop(1));
//            System.out.println(se.pop(1));
//            System.out.println(se.isEmpty(1));
//        }catch (EmptyStackException e){
//            System.out.println("It's empty");
//        }

        Stack_Hard sh = new Stack_Hard(3,5);
        try {
            sh.push(0,1);
            sh.push(0,2);
            sh.push(0,3);
            sh.push(0,4);
            sh.push(0,5);
            sh.push(0,6);
            sh.push(0,7);
            sh.push(0,8);
            sh.push(0,9);

            sh.push(1,11);
            sh.push(1,12);
            sh.push(1,13);
            sh.push(1,14);
            sh.push(1,15);

        }catch (FullStackException e){
            System.out.println("It's full");
        }

        try {
            System.out.println(sh.pop(0));
            System.out.println(sh.pop(0));
            System.out.println(sh.pop(0));
            System.out.println(sh.pop(0));
            System.out.println(sh.pop(0));
            System.out.println(sh.pop(0));
            System.out.println(sh.pop(0));
            System.out.println(sh.pop(0));
            System.out.println(sh.pop(0));

            System.out.println(sh.pop(1));
            System.out.println(sh.pop(1));
            System.out.println(sh.pop(1));
            System.out.println(sh.pop(1));
            System.out.println(sh.pop(1));
        }catch (Exception e){
            System.out.println("It's empty");
        }
    }
}
