
package pkgsynchronized;

import javax.swing.JFrame;

/**
 *
 * @author Eduardo
 */
public class Synchronized {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
       JFrame ventana = new JFrame();
       Screen screen = new Screen();
       ventana.add(screen);
       ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       ventana.setSize(800,600);
       ventana.setVisible(true);
       
    }
}
