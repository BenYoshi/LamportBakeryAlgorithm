/**
 * Created by Benny on 3/5/2017.
 */
public class Customer implements Runnable {
    private int id;
    private static boolean choosingChair[] = new boolean[10];
    private static int ticketChair[] = new int[10];
    private static boolean choosingBarber[] = new boolean[10];
    private static int ticketBarber[] = new int[10];

    public Customer(int id) {
        this.id = id;
        new Thread(this).start();
    }

    @Override
    public void run() {
        findAChair();
        findABarber();
    }


    private void findAChair() {
        choosingChair[id] = true;
        ticketChair[id] = this.id + 1;
        choosingChair[id] = false;

        for (int i = 0; i < 10; i++) {
            if (i == id)
                continue;
            while (choosingChair[i]){doNothing();}
            while (ticketChair[i] != 0 && ticketChair[i] < ticketChair[id]){doNothing();}
            if (ticketChair[i] == ticketChair[id] && i < id) {
                while (ticketChair[i] != 0){doNothing();}
            }
        }

        // critical section
        while (!Chair.isAvailable()){doNothing();}
        Chair.occupy();
        System.out.println(this.id + " found an open chair, there are " + Chair.available() + " open chairs left.");
        ticketChair[id] = 0;
        // end
    }

    private void findABarber() {
        choosingBarber[id] = true;
        ticketBarber[id] = this.id + 1;
        choosingBarber[id] = false;

        for (int i = 0; i < 10; i++) {
            if (i == id)
                continue;
            while (choosingBarber[i]){doNothing();}
            while (ticketBarber[i] != 0 && ticketBarber[i] < ticketBarber[id]){doNothing();}
            if (ticketBarber[i] == ticketBarber[id] && i < id) {
                while (ticketBarber[i] != 0){doNothing();}
            }
        }

        //critical section
        while (!Barber.isAvailable()){doNothing();}
        Barber.giveHaircut();
        System.out.println(this.id + " now has a barber, there are  " + Barber.available() + " available barbers.");
        leaveBarberShop();
        ticketBarber[id] = 0;
        //end
    }

    private void leaveBarberShop() {
        Chair.leave();
        Barber.leave();
        System.out.println(this.id + " has left the barber shop.");
    }
    
    private void doNothing() {
        System.out.print("");
    }
}

class Chair {
    private static int occupiedChairs = 0;

    static boolean isAvailable() {
        return occupiedChairs < 5;
    }

    static int available() {
        return 5 - occupiedChairs;
    }

    static void occupy() {
        occupiedChairs++;
    }

    static void leave() {
        occupiedChairs--;
    }
}

class Barber {
    private static int occupiedBarbers = 0;

    static boolean isAvailable() {
        return occupiedBarbers < 3;
    }

    static int available() {
        return 3 - occupiedBarbers;
    }

    static void giveHaircut() {
        occupiedBarbers++;
    }

    static void leave() {
        occupiedBarbers--;
    }
}
