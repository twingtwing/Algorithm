package 자바로_배우는_쉬운_자료구조.Queue;

public interface Queue {
    boolean isEmpty();
    void enQueue(char item);
    char deQueue();
    void delete();
    char peek();
}
