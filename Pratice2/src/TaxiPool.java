import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TaxiPool {
    private static final long EXPRIED_TIME_IN_MILISECOND = 1200;
    private static final int NUMBER_OF_TAXI = 4;
    private final List<Taxi> available;
    private final List<Taxi> inUse;
    private final AtomicInteger count ;
    private final AtomicBoolean waiting ;

    public TaxiPool() {
        available = Collections.synchronizedList(new ArrayList<>());
        inUse = Collections.synchronizedList(new ArrayList<>());
        count = new AtomicInteger(0);
        waiting = new AtomicBoolean(false);
    }
    public synchronized Taxi getTaxi(){
        if (!available.isEmpty()){
            Taxi taxi = available.get(0);
            inUse.add(taxi);
            return taxi;
        }
        if (count.get() == NUMBER_OF_TAXI){
            this.waitingUntilTaxiAvailable();
            return getTaxi();
        }
        Taxi taxi  = createTaxi();
        inUse.add(taxi);
        return taxi;
    }

    private void waitingUntilTaxiAvailable() {
        if(waiting.get()){
            waiting.set(false);
            throw new TaxiNotFoundException("Not taxi avaiable");
        }
        waiting.set(true);
        waiting(EXPRIED_TIME_IN_MILISECOND);
    }

    private void waiting(long time) {
        try {
            TimeUnit.MICROSECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public Taxi createTaxi(){
        waiting(200);
       Taxi taxi = new Taxi("Taxi "+ count.incrementAndGet());
        System.out.println(taxi.getName()+ "isCreated");
        return taxi;
    }

    public synchronized void release(Taxi taxi) {
        inUse.remove(taxi);
        available.add(taxi);
        System.out.println(taxi.getName() + " is free");
    }
}
