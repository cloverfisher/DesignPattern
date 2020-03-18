package observer_pattern.eventbus;

public class YMsg extends XMsg {

    int year = 13;

    @Override
    void msgcall() {
        super.msgcall();
        System.out.println("year="+year);
    }
}
