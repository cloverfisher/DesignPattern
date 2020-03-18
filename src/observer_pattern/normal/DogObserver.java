package observer_pattern.normal;

public class DogObserver implements Observer {
    @Override
    public void update() {
        System.out.println("Gotcha, WongWong");
    }
}
