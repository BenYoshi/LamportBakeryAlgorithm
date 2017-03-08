public class Main {
    public static void main(String[] args) {
//        runBarbershopSimulation();
//        runNTs();
    }

    // 2 barbers, 5 waiting chairs, first check barbers then chairs otherwise leave for some time and comeback.
    // NO wait or sleep. Use spinlocks - put a empty method inside the while loop that way it will pass control quicker.
    private static void runBarbershopSimulation() {
        for (int i = 0; i < 10; i++) {
            new Customer(i);
        }
    }

    //10 threads ordered by ID number.  lowest to highest adds 1 to counter waits until everyone is done. then next
    // task is multiply int x = 1 by two, each thread in order.
    // NO wait or sleep.
    public static void runNTs() {
        for (int i = 0; i < 10; i++) {
            new NT(i);
        }
    }
}
