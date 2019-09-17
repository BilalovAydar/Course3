package lessonl;

public class WaitNotifyClass {
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        WaitNotifyClass w1 = new WaitNotifyClass();
        Thread t1 = new Thread(()->w1.printa());
        Thread t2 = new Thread(()->w1.printb());
        Thread t3 = new Thread(()->w1.printc());
        t1.start();
        t2.start();
        t3.start();
    }

    private void printa() {
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A'){
                        mon.wait();
                    }
                    System.out.println('A');
                    currentLetter = 'B';
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printb() {
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B'){
                        mon.wait();
                    }
                    System.out.println('B');
                    currentLetter = 'C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void printc() {
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C'){
                        mon.wait();
                    }
                    System.out.println('C');
                    currentLetter = 'A';
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
