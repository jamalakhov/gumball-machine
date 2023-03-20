package after.imp;

import after.GumballMachine;
import after.State;
import lombok.Getter;

public class NoQuarterState implements State {
    @Getter
    public final String stateName;
    private final GumballMachine machine;

    public NoQuarterState(GumballMachine machine) {
        this.machine = machine;
        this.stateName = this.getClass().getSimpleName();
    }

    @Override
    public String insertQuarter() {
        machine.setState(machine.getHasQuarterState());
        return "You inserted a quarter.";
    }

    @Override
    public String ejectQuarter() {
        return "You have`t inserted a quarter.";
    }

    @Override
    public String ternCrank() {
        return "You turned but there`s no quarter.";
    }

    @Override
    public String dispense() {
        return "You need to pay first.";
    }

    @Override
    public void refill() {

    }
}