import box.Box;
import fruits.Apple;
import fruits.Orange;

public class Main {

    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        for (int i = 0; i < 10; i++){
            appleBox.addFruit(new Apple(1.5));
            orangeBox.addFruit(new Orange(2));
        }

        System.out.println("Weights are equal? " + appleBox.compare(orangeBox));
        Box<Apple> newAppleBox = new Box<>();
        System.out.println(orangeBox);
        System.out.println(appleBox);
        System.out.println("Transfering...");
        appleBox.transferFruits(newAppleBox);
        System.out.println(appleBox);
    }
}