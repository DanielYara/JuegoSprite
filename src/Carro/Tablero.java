/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Usuario9
 */
public class Tablero extends JPanel implements ActionListener, KeyListener{
    private Timer timer; 
    private ArrayList<Circulo> circulo;
    private Carro personajePrincipal;
    private Sound sonido;
    private int puntaje = 0;
    public Tablero(){
      this.sonido = new Sound("ball.wav");
      this.setFocusable(true);
      this.addKeyListener(this);
      this.personajePrincipal = new Carro(100,200);
      
      
      
      this.circulo = new ArrayList<Circulo>();
      this.circulo.add(new Circulo(20,20));
      this.circulo.add(new Circulo(100,80));
      this.circulo.add(new Circulo(80,120));
      this.timer = new Timer(50, this);
      this.sonido.loop();
      this.timer.start();
    }
    
    private void initBoard() throws Exception{
        addKeyListener(new TAdapter());
        setFocusable(true);
        
        setBackground(Color.WHITE);
    }
    
    protected Image loadImage(String imageName) {
       ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
         return image;
    }
    
    
    protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         Image fondo = loadImage("blue_background.png");
         Image suelo1 = loadImage("ground_loop.png");
         Image suelo2 = loadImage("ground_single.png");        
         for(int i = 0; i < (22*46); i+=22){
            g.setColor(Color.RED);
            g.drawImage(fondo, 0, 0, i, 600, 0, 300, 22, 800, this);
            //g.drawRect(i, 300, 112, 68);
         }
         int aux = 0;
         for(int i = 0; i < 10; i++){
            if((i%2)  == 0){ 
                g.drawImage(suelo1, aux, 500, 112, 68, this);
                aux += 90;
            }else if((i % 2) != 0){
                g.drawImage(suelo2, aux, 500, 111, 68, this);
                aux += 109;
            }
         }
         for(Circulo c: this.circulo)
            c.dibujar(g,this);
         this.personajePrincipal.dibujar(g,this);
         g.drawString("Puntaje " + puntaje, 40, 40);
    }
    
    private class TAdapter extends Tablero{

        @Override
        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_SPACE){
                System.out.println("VK_SPACE");
            }
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_SPACE: keyReleased(e);
            }
            //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        validarColisiones();
         for(Circulo c: this.circulo)
            c.mover();
            repaint();
        
    }
     
    
    public void validarColisiones(){
        Rectangle recPersonaje= this.personajePrincipal.obtenerRectangulo();
        ArrayList<Circulo> copia = (ArrayList<Circulo>) this.circulo.clone();
        for(Circulo c : circulo){
           Rectangle RecCir = c.obtenerRectangulo();
           if(recPersonaje.intersects(RecCir)){
               copia.remove(c);
               this.puntaje++;
           }
           this.circulo=copia;   
           
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
     
    }

    @Override
    public void keyPressed(KeyEvent e) {
       this.personajePrincipal.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
}
