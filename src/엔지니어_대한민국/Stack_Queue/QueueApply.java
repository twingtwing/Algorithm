package 엔지니어_대한민국.Stack_Queue;

import java.util.*;

/**
 * 1. 웅용 : 개와 고양이만 분양하는 분양소가 있다. 분양받는 사람은 동물의 종류만 구할 수 있고, 가장 오래 있는 순서로 자동으로 분양될 동물에 정해지는 클래스를 구현
 *          (단, 자바에서 제공되는 Linked List로 구현해야함)
 *          => 가장 오래된 이므로 Queue 자료구조를 사용해야함
 */
enum AnimalTupe{
    DOG,CAT
}

abstract class Animal{
    AnimalTupe type;
    String name;
    int order;

    Animal(AnimalTupe type, String name){
        this.type = type;
        this.name = name;
    }
    void setOrder(int order){
        this.order = order;
    }

    int getOrder(){
        return order;
    }

    String info(){
        return order + ") type : " + type + ", name : " + name;
    }

}

class Dog extends Animal{
    Dog(String name) {
        super(AnimalTupe.DOG, name);
    }
}

class Cat extends Animal{
    Cat(String name) {
        super(AnimalTupe.CAT, name);
    }
}

class AnimalShelter{
    LinkedList<Dog> dogs = new LinkedList<>();
    LinkedList<Cat> cats = new LinkedList<>();
    int order;

    AnimalShelter(){
        this.order = 1;
    }

    void enqueue(Animal animal){
        animal.setOrder(order);
        order++;
        if (animal.type == AnimalTupe.DOG){
            dogs.addLast((Dog) animal);
        }else if (animal.type == AnimalTupe.CAT){
            cats.addLast((Cat) animal);
        }

    }

    Animal dequeueDog(){
        return dogs.poll();
    }


    Animal dequeueCat(){
        return cats.poll();
    }

    Animal dequeue(){
        if (dogs.size() == 0 && cats.size() == 0){
            return null;
        }else if(dogs.size() == 0){
            return cats.poll();
        }else if(cats.size()==0){
            return dogs.poll();
        }
        Animal dog = dogs.peek();
        Animal cat = cats.peek();
        if (cat.order < dog.order){
            return cats.poll();
        }else{
            return dogs.poll();
        }
    }

}

public class QueueApply {
    public static void main(String[] args){
        Dog d1 = new Dog("d");
        Dog d2 = new Dog("o");
        Dog d3 = new Dog("g");
        Cat c1 = new Cat("c");
        Cat c2 = new Cat("a");
        Cat c3 = new Cat("t");

        AnimalShelter shelter = new AnimalShelter();
        shelter.enqueue(d1);
        shelter.enqueue(c1);
        shelter.enqueue(d2);
        shelter.enqueue(c2);
        shelter.enqueue(c3);
        shelter.enqueue(d3);

        System.out.println(shelter.dequeueDog().info());
        System.out.println(shelter.dequeueCat().info());
        System.out.println(shelter.dequeue().info());
        System.out.println(shelter.dequeue().info());
        System.out.println(shelter.dequeue().info());
        System.out.println(shelter.dequeue().info());
    }
}
