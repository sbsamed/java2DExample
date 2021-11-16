
package rectangel;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.util.Random;

class Surface extends JPanel {
    
    
   
        
    public void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
       
        float[] dash3 = {4f, 0f, 2f};
        

        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f);
       
        
        /*1.Yöntem*/
      Random random = new Random();
       int rred,rgreen,rblue;
      for(int y=0;y<800;y+=100){
            for (int x = 0; x <1000; x+=100) {
             rred=Math.abs(random.nextInt()) % 255;
             rgreen=Math.abs(random.nextInt()) % 255;
             rblue=Math.abs(random.nextInt()) % 255;
             g2d.setColor(new Color(rred, rgreen, rblue));
             g2d.fillRect(x, y, 100, 100);
            }
        }
        g2d.setColor(new Color(0,0,0));
       
        for(int y=0;y<=800;y+=100){
           
            g2d.setStroke(bs1);
            g2d.drawLine(0, y, 1000, y);
           }
     for(int x=0;x<=1000;x+=100){
            g2d.setStroke(bs1);
           g2d.drawLine(x, 0, x, 1000);
         }
    
      /*2.yöntem*/
  /*   Random random = new Random();
       int rred,rgreen,rblue;
        for(int y=0;y<800;y+=100){
            for (int x = 0; x <1000; x+=100) {
             rred=Math.abs(random.nextInt()) % 255;
             rgreen=Math.abs(random.nextInt()) % 255;
             rblue=Math.abs(random.nextInt()) % 255;
             
                g2d.setColor(new Color(0,0,0));
        	 g2d.setStroke(bs1);
        	  g2d.drawRect(x, y, 100, 100);
        	g2d.setColor(new Color(rred, rgreen, rblue));
              g2d.fillRect(x+1, y+1, 98, 98);     
            }
        }*/
        
        
     
       
       
       
        
    }

    @Override
    public void paintComponent(Graphics g) {
                
        super.paintComponent(g);
        doDrawing(g);
    }           
}

public class rectangel extends JFrame {

    public rectangel() {

        initUI();
    }

    private void initUI() {
        
        add(new Surface());
        
        setTitle("Rectangel");
        setSize(1007, 836);
                
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                rectangel ex = new rectangel();
                ex.setVisible(true); 
                ex.setResizable(false); 
            }
        });
    }
}



