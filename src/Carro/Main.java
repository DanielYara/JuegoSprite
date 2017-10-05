/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carro;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Usuario9
 */
public class Main {
  
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Tablero tablero = new Tablero();
        frame.setSize(new Dimension(990,600));
        frame.add(tablero); //adicionando el panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
}
