package observer_pattern;

import java.util.ArrayList;
import java.util.List;

public class PetPublisher implements Subject {
    private List<Observer> obs = new ArrayList<>();

    @Override
    public void register(Observer ob) {
        obs.add(ob);
    }

    @Override
    public void remove(Observer ob) {
        obs.remove(ob);
    }

    @Override
    public void notifyObserver() {
        for(Observer ob : obs){
            ob.update();
        }
    }
}


