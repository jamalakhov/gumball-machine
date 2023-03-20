package before;

// Автомат по выдаче жевательной резинке в виде шариков
public class GumballMachine {
    //Четыре состояни автомата
    final static int SOLD_OUT = 0;
    final static int NO_QUARTER = 1;
    final static int HAS_QUARTER = 2;
    final static int SOLD = 3;

    int state = SOLD_OUT;
    int count; // количество шариков в автомате

    public GumballMachine(int count) {
        this.count = count;
        if (count > 0) {
            state = NO_QUARTER;
        }
    }

    // В автомат бросили монетку
    public String insertQuarter() {
        String message;
        switch (state) {
            case HAS_QUARTER ->
                // В автомате уже есть монетка
                    message = "You can`t insert another quarter.";
            case NO_QUARTER -> {
                // Автомат принимает монетку
                state = HAS_QUARTER;
                message = "You inserted a quarter.";
            }
            case SOLD_OUT ->
                // Если шариков нет, то автомат отклоняет монетку
                    message = "You can`t insert a quarter, the machine is sold out.";
            case SOLD ->
                // Процесс покупки уже идет и нужно подождать его завершения
                    message = "Please wait, we`re already giving you a gumball.";
            default -> message = "System error";
        }
        return message;
    }

    // Покупатель пытается вернуть монетку
    public String ejectQuarter() {
        String message;
        switch (state) {
            case HAS_QUARTER -> {
                // Монетка возвращается
                message = "Quarter returned.";
                state = NO_QUARTER;
            }
            case NO_QUARTER ->
                // Монетки нет и вернуть ее невозможно
                    message = "You have`t inserted a quarter.";
            case SOLD_OUT ->
                // Шарик уже куплен и возврат невозможен
                    message = "Sorry, you already turned the crank.";
            case SOLD ->
                // Если шарики кончились, то и бросить монетку не получится, т.е. и возращать тогда нечего
                    message = "You can't inject, you have`t inserted a quarter yet.";
            default -> message = "System error";
        }
        return message;
    }

    // Покупатель пытается дернуть за рычаг
    public String ternCrank() {
        String message;
        switch (state) {
            case SOLD ->
                // Кто-то пытается обмануть автомат
                    message = "Turning twice doesn't get you another gumball.";
            case NO_QUARTER ->
                // Сначала нужно бросить монетку
                    message = "You turned but there`s no quarter.";
            case SOLD_OUT ->
                // Выдача невозможна, в автомате нет шариков
                    message = "You turned but there are not gumball.";
            case HAS_QUARTER -> {
                // Покупатель получает шарик
                state = SOLD;
                message = dispense();
            }
            default -> message = "System error";
        }
        return message;
    }

    // Вызывается для выдачи шарика
    private String dispense() {
        String message;
        switch (state) {
            case SOLD -> {
                //Автомат  в состоянии выдать покупку
                message = "A gumball comes rolling out the slot.";
                count -= 1;
                if (count == 0) {
                    System.out.println("Oops, out of gumballs.");
                    state = SOLD_OUT;
                } else {
                    state = NO_QUARTER;
                }
            }
            case NO_QUARTER ->
                // Обработка ошибки
                    message = "You need to pay first.";
            case SOLD_OUT ->
                // Обработка ошибки
                    message = "No gumball dispensed.";
            case HAS_QUARTER ->
                // Обработка ошибки
                    message = "No gumball dispensed";
            default -> message = "System error";
        }
        return message;
    }

    @Override
    public String toString() {
        return switch (state) {
            case 0 -> "SOLD_OUT";
            case 1 -> "NO_QUARTER";
            case 2 -> "HAS_QUARTER";
            case 3 -> "SOLD";
            default -> "ERROR";
        };
    }
}