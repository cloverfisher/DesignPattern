package observer_pattern;

public class CatObserver implements  Observer {
    @Override
    public void update() {
        System.out.println("gotcha, meow meow");
    }
}
