package fruits;

public class Fruit {
    private double weight;

    public Fruit(double weight){
        this.weight = weight;
    }

    public double getWeight(){
        return this.weight;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public String getName(){
        return "Fruit";
    }
}
