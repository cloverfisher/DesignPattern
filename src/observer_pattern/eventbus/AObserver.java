package observer_pattern.eventbus;

public class AObserver {


    @Subscribe
    public void function(XMsg n) {
        n.msgcall();
        System.out.println("this is myobserver1, function1");
    }

    @Subscribe
    public void functiony(YMsg n) {
        n.msgcall();
        System.out.println("this is myobserver2, function2");
    }
}
