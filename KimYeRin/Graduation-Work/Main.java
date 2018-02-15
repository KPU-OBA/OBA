package system;

import java.io.IOException;
import javax.swing.JFrame;

import view.Surface;

public class Main {
	 public static void main(String[] args) throws InterruptedException, IOException {
	        JFrame frame = new JFrame();
	        Surface panel = new Surface();
	        frame.setSize(500, 500);
	        frame.add(panel);
	        frame.setVisible(true);
	    }

}
