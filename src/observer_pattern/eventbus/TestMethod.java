package observer_pattern.eventbus;

public class TestMethod {

    public static void main(String[] args){
        AObserver myObserver1 = new AObserver();
        EventBus eventBus = new EventBus();
        eventBus.registry(myObserver1);
        YMsg testx = new YMsg();
        eventBus.post(testx);

//        AObserver a = new AObserver();
//        XMsg xMsg = new XMsg();
//        YMsg yMsg = new YMsg();
//        a.function(yMsg);
    }
}
