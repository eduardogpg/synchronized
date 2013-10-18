/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsynchronized;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Eduardo
 */
public class Screen extends JPanel implements ActionListener,KeyListener {

    private Timer timer;
    private int pos = 0;
    private String Message = "S -> Wait All \t D-> Restart One \n";
    private String MessageDos = " A-> Restart All \n SPACE ->New Ship";
    
    public Image Background;
    public ImageIcon IconBackgroun;
    boolean life = false;
    JButton Credits;
    ArrayList ship;
    private Font font;
    private final Color MyColor=new Color( 250, 250, 250);
    
    public Screen(){
    ship = new ArrayList();
    
    Credits = new JButton("Creditos");
    
    IconBackgroun = new ImageIcon("Imagenes/fondo.jpg");
    Background = IconBackgroun.getImage();
    
    timer = new Timer(4,this); 
        timer.start();
        setFocusable(true);
        addKeyListener(this);
        
     Credits.addActionListener(this);
     add(Credits);
     font = new Font("SansSerif",Font.BOLD,18);   
    }
    
    public void paint(Graphics g){
          g.drawImage(Background,0,0, this);
          Graphics2D g2 = (Graphics2D)g;
          g2.setFont(font);
          g2.setColor(this.MyColor);
          
           g2.drawString(this.Message,50,50);
           g2.drawString(this.MessageDos,50,100);
           
           
          if(this.ship.size()>0)
              for(int x=0;x<this.ship.size();x++){
                  Enemy LittleShip = (Enemy)ship.get(x);

                  g2.drawImage(LittleShip.getimage(), LittleShip.getX(),LittleShip.getY() ,null);
                }
    }
    
    public void actionPerformed(ActionEvent e) {
       repaint();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int Key = e.getKeyCode();
    
        if(Key==KeyEvent.VK_SPACE){
            MakeShip();
        }else if(Key==KeyEvent.VK_S){
            if(this.ship.size()>0){
                for(int x =0;x<ship.size();x++){
                    Enemy LittleShip = (Enemy) this.ship.get(x);
                    LittleShip.ShipWait();
                }
           }
                
        }else if(Key==KeyEvent.VK_D){
             if(this.ship.size()>0){
                Enemy LittleShip = (Enemy) this.ship.get(pos);
                  NotyOne(LittleShip);
                  CheckPosition();
          
          }
        }else if(Key==KeyEvent.VK_A){
              
              for(int x =0;x<ship.size();x++){
                  Enemy LittleShip = (Enemy) this.ship.get(x);
                  NotyAll(LittleShip);
              }
              
          }else if(Key==KeyEvent.VK_C){
              c= new Creditos();
              c.setVisible(true);
              
          }
 
 
    }
    Creditos c;
    private void CheckPosition(){
        if(pos < this.ship.size()-1)
            pos++;
        else
            pos=0;
    }
  private void NotyAll(Enemy LittleShip){
                     synchronized (LittleShip) {
                         LittleShip.notifyAll();
                        try {
                            LittleShip.ShipNoti();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   }
  
  }
  private void NotyOne(Enemy LittleShip){
      synchronized (LittleShip) {
                    try {
                        LittleShip.ShipNoti();
                        LittleShip.notify();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
  }
    public void keyReleased(KeyEvent e) {
    }
    
    private void MakeShip(){
        Enemy LittleShip = new Enemy();
        ship.add(LittleShip);
        LittleShip.start();
    }
}
       /*
   http://www.javamex.com/tutorials/synchronization_wait_notify.shtml
   */