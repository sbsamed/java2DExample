
package odev_2_2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class egikAtis extends JFrame {
    
     List<Point2D> topunKonumlari= new ArrayList<>();
     double G = 9.8; 
     int size = 900, yariCap = 20;
     double xSpeed, ySpeed, lastPointX, lastPointY;
     double time, delayy = 0.07 ; 
     int tekrarhizi = 1; 
     Timer timer;
     double startX, startY, ballX, ballY;
    JTextField tf1,tf2,tf3;  
    JLabel l1,l2,l3; 
    JButton b1;  
    int aci,hiz,tekrar,adet=0;
    public egikAtis(){
       
        JFrame f= new JFrame(); 
        l1=new JLabel("Açı Değeri ");  l1.setBounds(50,20, 100,30);  
        tf1=new JTextField();  tf1.setBounds(50,50,150,20);  
        l2=new JLabel("Hız Değeri ");  l2.setBounds(50,75, 100,30);  
        tf2=new JTextField();  tf2.setBounds(50,100,150,20);  
        l3=new JLabel("Tekrarlama");  l3.setBounds(50,120, 100,30); 
        tf3=new JTextField();  tf3.setBounds(50,150,150,20);  
        b1=new JButton("run");  b1.setBounds(50,200,70,70);  
       
       f.add(tf1);f.add(tf2);f.add(tf3);f.add(b1); f.add(l1);f.add(l2); f.add(l3);
        f.setSize(400,400);  f.setLayout(null);  f.setVisible(true);  
          b1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               
                String s1=tf1.getText();  
                String s2=tf2.getText();  
                String s3=tf3.getText(); 
                 aci=Integer.parseInt(s1);  
                 hiz=Integer.parseInt(s2); 
                 tekrar=Integer.parseInt(s3); 
                  basla();
            }
        });
       
    
    }
   
     public void basla(){
        Surface surface  = new Surface();
        add(surface, BorderLayout.CENTER);
        ballX= lastPointX = startX = 10;
        ballY = lastPointY = startY = 880;
        kullaniciInput();

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        timer = new Timer(tekrarhizi, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                surface.topHareketi();
                surface.repaint();
                if(ballX>900 || ballY>900 ){
                ballX= lastPointX = startX = 10;
                ballY = lastPointY = startY = 880;
                time=0.07;
                topunKonumlari.clear();
                surface.topHareketi();
                surface.repaint();
                 adet+=1;
                }
                if(adet==tekrar){
                    timer.stop(); 
                }
                
            }
        });
        timer.start(); 
     }

    private void kullaniciInput() {
        xSpeed = hiz * Math.cos(aci * (Math.PI / 180));
        ySpeed = hiz * Math.sin(aci * (Math.PI / 180));
    }

    class Surface extends JPanel implements ActionListener {
  
        public Surface() {
            setPreferredSize(new Dimension(size, size));
          
        }
      
        @Override
        public void paint(Graphics g) {
           super.paint(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2d.setColor(Color.blue);
           
            g2d.fillOval((int)ballX,(int)ballY,yariCap,yariCap);

            if((Math.abs(lastPointX - ballX)>=1) && (Math.abs(lastPointY - ballY)>=1) ) {
                topunKonumlari.add(new Point2D.Double(ballX, ballY));
                lastPointX = ballX; lastPointY = ballY;
            }

            topTakip(g2d);
        }

        private void topTakip(Graphics2D g2d) {
            if(ballX>900 ||ballY>900 ){
               
                super.paint(g2d);
            }
            else{
                   
            g2d.setColor(Color.black);
            for(int i=0; i < (topunKonumlari.size()-1); i++) {

                Point2D from = topunKonumlari.get(i);
                Point2D to = topunKonumlari.get(i+1);
                g2d.drawLine((int)from.getX(),(int)from.getY(), (int)to.getX(), (int)to.getY());
            }
            }
        }

        private void topHareketi() {

            ballX = startX + (xSpeed * time);//yataydaki  menzil
            ballY = startY - ((ySpeed *time)-(0.5 *G * Math.pow(time, 2)));//zamana göre yatadaki hareket
            time += delayy; 
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException(); 
        }

    }
    public static void main(String[] args) {

        new egikAtis();
    }
}