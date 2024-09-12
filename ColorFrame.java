package monpackage;

import java.awt.Color;
import javax.swing.JFrame;

public class ColorFrame {

	private JFrame frame;
	
	ColorFrame(int x, int y)
	{
		frame = new JFrame("Chenillard");
		frame.setSize(300,300);
		frame.setLocation(x, y);
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setVisible(true);
	}
	
	public void setRouge() {
		frame.getContentPane().setBackground(Color.RED);
	}

	public void setGreen() {
		frame.getContentPane().setBackground(Color.GREEN);
	}
	
	public void close() {
		frame.dispose();		
	}
}
