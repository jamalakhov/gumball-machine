import after.GumballMachine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAfter {

    final static String state = "NoQuarterState";
    private GumballMachine machine;

    @Before
    public void setUp() {
        int count = 100;
        machine = new GumballMachine(count);
    }

    @Test
    public void testPayGumball() {
        machine.insertQuarter();
        machine.ternCrank();

        var expected = "A gumball comes rolling out the slot.";
        var actual = machine.releaseBall();

        assertEquals(expected, actual);
        assertEquals(state, machine.toString());
    }

    @Test
    public void testTurnCrankAfterEjectQuarter() {
        machine.insertQuarter();
        machine.ejectQuarter();
        machine.ternCrank();

        var expected = "You need to pay first.";
        var actual = machine.releaseBall();

        assertEquals(expected, actual);
        assertEquals(state, machine.toString());
    }

    @Test
    public void testEjectQuarterAfterIssuingGumball() {
        machine.insertQuarter();
        machine.ternCrank();
        machine.releaseBall();

        machine.insertQuarter();
        machine.ternCrank();
        machine.releaseBall();

        var expected = "You have`t inserted a quarter.";
        var actual = machine.ejectQuarter();

        assertEquals(expected, actual);
        assertEquals(state, machine.toString());
    }

    @Test
    public void testSomeTimeInsertQuarterAndTernCrank() {
        machine.insertQuarter();
        machine.insertQuarter();
        machine.ternCrank();
        machine.releaseBall();

        machine.insertQuarter();
        machine.ternCrank();
        machine.releaseBall();

        machine.insertQuarter();
        machine.ternCrank();

        var expected = "A gumball comes rolling out the slot.";
        var actual = machine.releaseBall();

        assertEquals(expected, actual);
        assertEquals(state, machine.toString());
    }

    @Test
    public void testWinnerGame() {
        for (int i = 0; i < 50; i++) {
            machine.insertQuarter();
            machine.ternCrank();
            machine.releaseBall();
        }

        assertTrue(machine.getCountWinner() > 0);
        System.out.println("count winner: " + machine.getCountWinner());
    }
}