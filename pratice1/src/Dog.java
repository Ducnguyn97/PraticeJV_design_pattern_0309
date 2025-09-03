public class Dog extends Animal {

    @Override
    public void eat() {
        System.out.println("Dog eat");
    }

    @Override
    public void sleep() {
        System.out.println("Dog sleep");

    }

    @Override
    public String makeSound() {
        return "Woof";
    }
}
