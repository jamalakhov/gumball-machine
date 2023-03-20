package after.imp;

import after.GumballMachine;
import after.State;
import lombok.Getter;

import java.util.Random;

public class HasQuarterState implements State {
    @Getter
    public final String stateName;
    private final Random randomWinner;
    private final GumballMachine machine;

    public HasQuarterState(GumballMachine machine) {
        this.machine = machine;
        this.randomWinner = new Random(System.currentTimeMillis());
        this.stateName = this.getClass().getSimpleName();
    }

    @Override
    public String insertQuarter() {
        return "You can`t insert another quarter.";
    }

    @Override
    public String ejectQuarter() {
        machine.setState(machine.getNoQuarterState());
        return "Quarter returned.";
    }

    @Override
    public String ternCrank() {
        int winner = randomWinner.nextInt(10);
        if (winner == 0 && machine.getCountBalls() > 1) {
            machine.setCountWinner(machine.getCountWinner() + 1);
            machine.setState(machine.getWinnerState());
        } else {
            machine.setState(machine.getSoldState());
        }
        return "You terned...";
    }

    @Override
    public String dispense() {
        return "No gumball dispensed";
    }

    @Override
    public void refill() {

    }
}