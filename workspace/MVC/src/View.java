import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class View {
	JFrame frame;
	JTextField field;
	JButton button;
	JLabelObserver label, label2, label3;
	
	public JLabelObserver getLabel2() {
		return label2;
	}
	public JLabelObserver getLabel3() {
		return label3;
	}
	public JLabelObserver getLabel() {
		return label;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JTextField getField() {
		return field;
	}
	public JButton getButton() {
		return button;
	}
	
	public void init() {
		frame = new JFrame("Exemple POO");
		field = new JTextField();
		field.setColumns(10);
		button = new JButton("OK");
		label = new JLabelObserver("label 1");
		label2 = new JLabelObserver("label 2");
		label3 = new JLabelObserver("label 3");
		
		frame.setLayout(new FlowLayout());
		frame.getContentPane().add(field);
		frame.getContentPane().add(button);
		frame.getContentPane().add(label);
		frame.getContentPane().add(label2);
		frame.getContentPane().add(label3);
		
		frame.setVisible(true);
		frame.pack();
	}
	

}
