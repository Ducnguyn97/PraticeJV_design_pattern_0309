public class Cat extends Animal {

    @Override
    public void eat() {
        System.out.println("cat aet");

    }

    @Override
    public void sleep() {
        System.out.println("cat sleep");

    }

    @Override
    public String makeSound() {
        return "Meow";
    }
}
