package box;

import fruits.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> implements Comparable<Box<?>>{
    private List<T> fruitList = new ArrayList<>();

    public Box(){
    }

    public Box(List<T> fruitList){
        this.fruitList = fruitList;
    }

    public void addFruit(T fruit){
        fruitList.add(fruit);
    }

    public T getFruit(int index){
        return fruitList.get(index);
    }

    public List<T> getFruitList(){
        return fruitList;
    }

    public double weight(){
        double sum = 0;
        for (T fruit : fruitList){
            sum += fruit.getWeight();
        }
        return sum;
    }

    public boolean compare(Box<?> box){
        return this.compareTo(box) == 0;
    }

    public void transferFruits(Box<T> box){
        box.fruitList.addAll(this.fruitList);
        this.fruitList.clear();
    }

    @Override
    public int compareTo(Box<?> box) {
        return Double.compare(this.weight(), box.weight());
    }

    @Override
    public String toString() {
        if (this.fruitList.isEmpty()){
            return "Empty box.";
        }
        return this.fruitList.get(0).getName() + " box. "
        + "Amount: " + fruitList.size();
    }
}
