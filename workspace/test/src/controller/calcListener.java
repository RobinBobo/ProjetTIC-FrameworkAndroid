package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import view.Window;
import model.Calculator;
import model.Memory;

public class calcListener implements ActionListener {
			
	  private Window w;
	  private Memory m;
	  private Calculator c = new Calculator();	  
	  
	  public calcListener (Window w, Memory m) {
		this.w = w;
		this.m = m;
		
		w.addActionListener(this);
	  }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("test");
		 m.MS(c.addition(Integer.parseInt(w.getA().getText()), Integer.parseInt(w.getB().getText())));
		 w.getRes().setText(String.valueOf(m.MR()));		
	}
	
}
	
		
	  

