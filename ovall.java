/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oval;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;


class Surface extends JPanel implements ActionListener {

    private final int DELAY = 150;
    private Timer timer;
    int r=40;
    int adet=0;

    public Surface() {

        initTimer();
    }

    private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public Timer getTimer() {
        
        return timer;
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        Random random = new Random();
        
            Area a2 = new Area(new Ellipse2D.Double( 500-(r/2), 500-(r/2), r, r));
            adet+=1;
            r+=40;
            
           int rred=Math.abs(random.nextInt()) % 255;
           int  rgreen=Math.abs(random.nextInt()) % 255;
           int rblue=Math.abs(random.nextInt()) % 255;
           g2d.setPaint(new Color(rred,rgreen,rblue));
           
           g2d.drawOval(500-(r/2), 500-(r/2), r, r);
          
           Area a1 = new Area(new Ellipse2D.Double(500-(r/2), 500-(r/2), r, r));
            
           a1.subtract(a2);
           
           g2d.fill(a1);
           
           
    }

    @Override
    public void paintComponent(Graphics g) {
         if(adet==24){
             adet=0;
             r=40;
           
             super.paintComponent(g);
             
         }
        
       doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

public class ovall extends JFrame {

    public ovall() {

        initUI();
    }

    private void initUI() {

         Surface surface = new Surface();
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
            }
        });

        setTitle("Oval");
        setSize(1000, 1000);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                ovall ex = new ovall();
                ex.setVisible(true);
            }
        });
    }
}