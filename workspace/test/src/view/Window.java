package view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Memory;
import controller.calcListener;

public class Window extends JFrame{
	private JPanel pan = new JPanel();
	private JButton equal = new JButton("=");
	private JLabel add = new JLabel("+");
	private JTextField a = new JTextField(10);
	private JTextField b = new JTextField(10);
	private JTextField res = new JTextField(10);


	public Window(){
		this.setTitle("Calculator XR2000");
		this.setSize(500, 70);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());

		pan.add(a);
		pan.add(add);
		pan.add(b);
		pan.add(equal);
		pan.add(res);
		this.setContentPane(pan);
		this.setVisible(true);
		
		//equal.addActionListener(new calcListener(this,new Memory()));
	} 

	public JTextField getA () {
		return a;		
	}
	
	public JTextField getB () {
		return b;		
	}
	
	public JTextField getRes () {
		return res;		
	}
	
	public void addActionListener (ActionListener c) {
		System.out.println("addactionlistener");
		equal.addActionListener(c);
	}	
}