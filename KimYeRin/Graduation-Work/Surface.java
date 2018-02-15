package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Surface extends JPanel {
    private void doDrawing(Graphics g) {
    	
    	ImageIcon icon = new ImageIcon("C:\\Users\\99101\\Desktop\\라이언.png");
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Image img = icon.getImage();
        for(int x = 0 ; x < 4 ; x++) {
            for(int y = 0 ; y < 4 ; y++) {
                g2d.drawRect(x*80,y*80, 80,80);
                
            }
        }
        g.drawImage(img, 0, 0, 80, 80 , this);
        
        int arr[] = {0, 80, 160, 240};
		Random ran = new Random();
		int a, b;
        
        for(int x=0; x<3; x++) {
        	a = arr[ran.nextInt(4)];
    		b = arr[ran.nextInt(4)];
    		/*if(a == 0 && b == 0) {
    			a = arr[ran.nextInt(4)];M
        		b = arr[ran.nextInt(4)];
    		}*/
    		g.fillRect(a, b, 80, 80);
	        g.setColor(Color.BLACK);
        }
        
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        
    }
}
