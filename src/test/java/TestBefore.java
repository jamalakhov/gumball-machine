import before.GumballMachine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBefore {
    final static String state = "NO_QUARTER";
    private GumballMachine machine;

    @Before
    public void setUp() {
        int count = 5;
        machine = new GumballMachine(count);
    }

    @Test
    public void testPayGumball() {
        machine.insertQuarter();

        var expected = "A gumball comes rolling out the slot.";
        var actual = machine.ternCrank();

        assertEquals(expected, actual);
        assertEquals(state, machine.toString());
    }

    @Test
    public void testTurnCrankAfterEjectQuarter(){
        machine.insertQuarter();
        machine.ejectQuarter();

        var expected = "You turned but there`s no quarter.";
        var actual = machine.ternCrank();

        assertEquals(expected, actual);
        assertEquals(state, machine.toString());
    }

    @Test
    public void testEjectQuarterAfterIssuingGumball(){
        machine.insertQuarter();
        machine.ternCrank();
        machine.insertQuarter();
        machine.ternCrank();

        var expected = "You have`t inserted a quarter.";
        var actual = machine.ejectQuarter();

        assertEquals(expected, actual);
        assertEquals(state, machine.toString());
    }

    @Test
    public void testSomeTimeInsertQuarterAndTernCrank(){
        machine.insertQuarter();
        machine.insertQuarter();
        machine.ternCrank();
        machine.insertQuarter();
        machine.ternCrank();
        machine.insertQuarter();

        var expected = "A gumball comes rolling out the slot.";
        var actual = machine.ternCrank();

        assertEquals(expected, actual);
        assertEquals(state, machine.toString());
    }
}