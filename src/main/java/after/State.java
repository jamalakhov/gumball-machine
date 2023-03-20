package after;

public interface State {
    String getStateName();
    String insertQuarter();
    String ejectQuarter();
    String ternCrank();
    String dispense();
    void refill();
}