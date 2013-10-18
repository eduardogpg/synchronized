/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsynchronized;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Eduardo
 */
public class Enemy extends Thread{
    
    Syncro LifeAlien;
    private ArrayList pathShip;
    
    private ImageIcon IconNave;
    private Image Nave;
    private int posY=150,posX=100;
    private int Speed= 10;
    private boolean life= true;

    
    public Enemy(){
        IconNave= new ImageIcon("Imagenes/Marciano2A.jpg");
        Nave = IconNave.getImage();
        pathShip= new ArrayList();
        pathShip= new ArrayList();
        LoadImg();
        LifeAlien= new Syncro();
    }
    private void LoadImg(){
        pathShip.add("Imagenes/Marciano2A.jpg");
        pathShip.add("Imagenes/Marciano2B.jpg");
    }
    private boolean BWaite= false;
    public void run(){
        while(life){
            try {
             synchronized(this){
               
                if(!BWaite)
                    behavior();
                else{
                    System.err.println("this will Stop");
                    this.wait();
                    System.err.println("Finished this method");}
            }
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
    }
    public void ShipWait(){
       BWaite= true;
    }
    public void ShipNoti() throws InterruptedException{
         BWaite= false;
    }
    
    int posI=0;
    private boolean BH= false;
    private void NextImge(){
        String path = (String) this.pathShip.get(posI);
        IconNave= new ImageIcon(path);
        Nave = IconNave.getImage();
        
    }
    private void ChangeImg(){
        if(this.BH==false){
           if(this.posI<this.pathShip.size()-1){
                 this.NextImge();
                 this.posI++;
                 }else
                    this.BH=true;
          }else{
               if(this.posI>0){
                this.NextImge();
                this.posI--;
                }else
                    this.BH= false;
                }
                        

    }
    private boolean Right= true;
    private boolean Down= true;
    
    private void behavior(){
    if((Right) &&(Down))
        Right();
    else if(Down)
        Down();
    else if(!Right)
        Left();
    else if((!Down))
        Up();
    
    ChangeImg();
    }
    
     private void Up(){
          if(this.posY >150)
            this.posY = this.posY- this.Speed;
          else{
              Down= true;
              
          }
          }
    private void Down(){//Funciona al 100
        if(this.posY < 400)
            this.posY = this.posY + this.Speed;
        else
            Down= false;
     }   
    private void Left(){//Funciona al 100
        if(this.posX>0)
            this.posX = this.posX- this.Speed;
       else
            this.Right= true;
    }
    private void Right(){
        if(this.posX<650)
           this.posX = this.posX+ this.Speed;
        else
            Right= false;
    }
        
    public Image getimage(){
     return this.Nave;
    }

    public int getX(){
        return this.posX;
}
    public int getY(){
        return this.posY;
    }
   public Rectangle Rectangulo(){
     int High;
     int Width;

     High = this.Nave.getHeight(null);
     Width = this.Nave.getWidth(null);
     return new Rectangle(this.posX,this.posY,High,Width);
    }
    
}
