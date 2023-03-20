package after.imp;

import after.GumballMachine;
import after.State;
import lombok.Getter;

public class SoldOutState implements State {
    @Getter
    public final String stateName;
    private final GumballMachine machine;

    public SoldOutState(GumballMachine machine) {
        this.machine = machine;
        this.stateName = this.getClass().getSimpleName();
    }

    @Override
    public String insertQuarter() {
        return "You can`t insert a quarter, the machine is sold out.";
    }

    @Override
    public String ejectQuarter() {
        return "You can't inject, you have`t inserted a quarter yet.";
    }

    @Override
    public String ternCrank() {
        return "You turned but there are not gumball.";
    }

    @Override
    public String dispense() {
        return "No gumball dispensed.";
    }

    @Override
    public void refill() {
        machine.setState(machine.getNoQuarterState());
    }
}