
package pkgsynchronized;

/**
 *
 * @author Eduardo
 */
public class Syncro extends Thread{
    
    public void WaitMet() throws InterruptedException {
        synchronized (this) {
            wait();
        }
    }
 
    public void NotiMet() throws InterruptedException {
         
        Thread.sleep(2000);
        synchronized (this) {
            notify();
            Thread.sleep(5000);
        }
    }
}
