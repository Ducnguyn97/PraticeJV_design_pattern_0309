public class TestAnimal {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();

        dog.eat();
        cat.eat();

        System.out.println("Dog make sound: "+ dog.makeSound());
        System.out.println("Cat make sound: "+ cat.makeSound());
    }
}
