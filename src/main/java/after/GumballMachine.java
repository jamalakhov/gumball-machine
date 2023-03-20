package after;

import after.imp.*;
import lombok.Getter;
import lombok.Setter;

@Getter
public class GumballMachine {
    private final State soldOutState;
    private final State noQuarterState;
    private final State hasQuarterState;
    private final State soldState;
    private final State winnerState;
    @Setter
    private State state;

    @Setter
    int countWinner = 0;
    int countBalls;

    public GumballMachine(int countBalls) {
        this.countBalls = countBalls;
        this.soldOutState = new SoldOutState(this);
        this.noQuarterState = new NoQuarterState(this);
        this.hasQuarterState = new HasQuarterState(this);
        this.soldState = new SoldState(this);
        this.winnerState = new WinnerState(this);

        if (countBalls > 0) {
            state = noQuarterState;
        } else {
            state = soldState;
        }
    }

    public String insertQuarter() {
        return state.insertQuarter();
    }

    public String ejectQuarter() {
        return state.ejectQuarter();
    }

    public String ternCrank() {
        return state.ternCrank();
    }

    public String releaseBall() {
        return state.dispense();
    }

    public void refill(int countBalls) {
        this.countBalls += countBalls;
        state.refill();
    }

    public void setCountBalls() {
        if (countBalls > 0) {
            countBalls -= 1;
        }
    }

    @Override
    public String toString() {
        return state.getStateName();
    }
}