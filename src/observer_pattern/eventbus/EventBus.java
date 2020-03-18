package observer_pattern.eventbus;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EventBus {
    private Executor executor;
    private ObserverRegistry registry  = new ObserverRegistry();

    public EventBus(){
        //Executor executor = Executors.newFixedThreadPool(3);
        this(Executors.newFixedThreadPool(3));
    }

    public EventBus(Executor executor){
        this.executor = executor;
    }

    public void registry(Object object){
        registry.register(object);
    }

    public void post(Object event){
        List<ObserverAction> observerActions = registry.getMatchedObserverActions(event);
        for( ObserverAction observerAction : observerActions){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    observerAction.execute(event);
                }
            });
        }
    }
}
