package observer_pattern;

public class DogObserver implements Observer {
    @Override
    public void update() {
        System.out.println("Gotcha, WongWong");
    }
}
