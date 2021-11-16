
package odev_2_1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class sincos extends JFrame {
    JTextField tf1,tf2 ; 
    JLabel l1,l2; 
    JButton b1;  
    double genlik,frekans;

    public sincos() {

        
        JFrame f= new JFrame(); 
        
        l1=new JLabel("Genlik(1-5)");  l1.setBounds(50,20, 100,30);  
        tf1=new JTextField();  tf1.setBounds(50,50,150,20);  
        l2=new JLabel("Frekans(1-5)");  l2.setBounds(50,75, 100,30);  
        tf2=new JTextField();  tf2.setBounds(50,100,150,20);  
       
       
       
        b1=new JButton("run");  b1.setBounds(50,200,70,70);  
       
        
       f.add(tf1);f.add(tf2);f.add(b1); f.add(l1);f.add(l2);
        f.setSize(400,400); f.setLayout(null);   f.setVisible(true);  
          b1.addActionListener(new ActionListener()
        {
            
            public void actionPerformed(ActionEvent e)
            {
               
                String s1=tf1.getText();  
                String s2=tf2.getText();  
              
                 genlik=Double.parseDouble(s1);  
                 frekans=Double.parseDouble(s2); 
                
                  initUI();
            }
        });
    }

    private void initUI() {
        
        add(new Surface());
        
        setTitle("SinCos");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



class Surface extends JPanel {
    double gSin(double y) {
        return Math.sin(y);
    }

    double gCos(double y) {
        return Math.cos(y);
    }
    
     double gCot(double y) {
        return Math.cos(y)/Math.sin(y);
     }
     
      double gTan(double y) {
        return Math.sin(y)/Math.cos(y);
      }

        
    public void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(  //cizgilerin Netligini ayarlamak için
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);    
        g2d.setRenderingHints(rh);

        g.drawLine(400, 0, 400, 800);
        g.drawLine(0, 400, 800, 400);

    
          
         
          g.setColor(Color.red);
          for(double x=-400;x<=400;x=x+0.01)
        {
            double y =genlik* 50 * gCos(frekans* x*(Math.PI/180));
            int Y = (int)y;
            int X = (int)x;
            g.drawLine(400+X,400-Y,400+X,400-Y);
        }
          
          
          g.setColor(Color.blue);
          for(double x=-400;x<=400;x=x+0.01)
        {
            double y = genlik* 50 * gSin( frekans* x*(Math.PI/180));
            int Y = (int)y;
            int X = (int)x;
            g.drawLine(400+X,400-Y,400+X,400-Y);
        }
          g.setColor(Color.black);
           for(double x=-400;x<=400;x=x+0.06)
        {
            double y = 100 * gCot(x*(Math.PI/180));
            int Y = (int)y;
            int X = (int)x;
            g.drawLine(400+X,400-Y,400+X,400-Y);
        }
          g.setColor(Color.blue);
          for(double x=-400;x<=400;x=x+0.06)
        {
            double y = 100 * gTan(x*(Math.PI/180));
            int Y = (int)y;
            int X = (int)x;
            g.drawLine(400+X,400-Y,400+X,400-Y);
        }
        
        
    }

    @Override
    public void paintComponent(Graphics g) {
                
       
        doDrawing(g);
    }           
}



    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                sincos ex = new sincos();
                
                ex.setResizable(false); 
            }
        });
    }
}