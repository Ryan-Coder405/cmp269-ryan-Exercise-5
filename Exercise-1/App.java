
public class App {
    
    public static void main(String[] args) throws InterruptedException {
        GreeterTask Task = new GreeterTask();
        Thread Lehman_Thread_1 = new Thread(Task);
        Thread Lehman_Thread_2 = new Thread(Task);
        
        System.out.println("First Thread");
        Lehman_Thread_1.run();
        System.out.println("Second Thread");
        Lehman_Thread_2.run();

        System.out.println("Main Thread stuff");
        //Lambda expression to immediately put this thread to sleep after creation
        Thread mainThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainThread.getState()); //print state after creation
        mainThread.start(); //starting the thread
        System.out.println(mainThread.getState()); //print state after start()
        //putting it to sleep to ensure its sleeping
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(mainThread.getState()); //getting the state while it's sleeping
        //I need to check start after the thread is finished, leading to this
        try {
            mainThread.join(); //this method waits for the thread to die, allowing us to check state after its done
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(mainThread.getState()); //printing state after its done

        BankAccount account = new BankAccount();

        Runnable takeMoney = () -> {
            account.withdraw(700);
        };

        Thread husband = new Thread(takeMoney);
        Thread wife = new Thread(takeMoney);
        
        System.out.println("Balance: " + account.getBalance());
        husband.start();
        wife.start();

        husband.join();
        wife.join();
        System.out.println("Balance: " + account.getBalance());
        
        Runnable heavyCalculation = () -> {
            int result = 0;
            for (int i = 0; i< 1_000_000_000; i++){
                result++;
            } 
            System.out.println("Calculation Finished: " + result);
        };

        Thread heavyCalc = new Thread(heavyCalculation);
        heavyCalc.start();
        heavyCalc.join();
        
    }
}
