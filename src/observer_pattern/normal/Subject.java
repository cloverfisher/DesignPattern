package observer_pattern.normal;

public interface Subject {
    public void register(Observer ob);

    public void remove(Observer ob);

    public void notifyObserver();
}
