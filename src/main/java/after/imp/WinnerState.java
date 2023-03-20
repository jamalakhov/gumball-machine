package after.imp;

import after.GumballMachine;
import after.State;
import lombok.Getter;

public class WinnerState implements State {
    @Getter
    public final String stateName;
    private final GumballMachine machine;

    public WinnerState(GumballMachine machine) {
        this.machine = machine;
        this.stateName = this.getClass().getSimpleName();
    }

    @Override
    public String insertQuarter() {
        return "Please wait, we`re already giving you a gumball.";
    }

    @Override
    public String ejectQuarter() {
        return "You can't inject, you have`t inserted a quarter yet.";
    }

    @Override
    public String ternCrank() {
        return "Turning twice doesn't get you another gumball.";
    }

    @Override
    public String dispense() {
        machine.setCountBalls();
        String message = "A gumball comes rolling out the slot.";

        if (machine.getCountBalls() == 0) {
            machine.setState(machine.getSoldOutState());
        } else {
            machine.setCountBalls();
            message = "YOU'RE A WINNER! You got two gumballs for your quarter.";

            if (machine.getCountBalls() > 0) {
                machine.setState(machine.getNoQuarterState());
            } else {
                machine.setState(machine.getSoldOutState());
                System.out.println("Oops, out of gumballs.");
            }
        }
        return message;
    }

    @Override
    public void refill() {

    }
}