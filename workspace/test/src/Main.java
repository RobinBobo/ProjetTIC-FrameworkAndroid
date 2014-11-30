import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.calcListener;
import view.Window;
import model.Calculator;
import model.Memory;


public class Main {
	
	public static void main(String [] args) {		
		
		//Calculator c = new Calculator();
		Memory m = new Memory();
		/*
		System.out.println("----------Caclulatrice----------");
		
		System.out.println("6+5MS");
		m.MS(c.addition(6, 5));
		System.out.println("Résultat = "+ m.MR());
		
		System.out.println("7/2 M+");
		
		m.mPlus(c.division(7,2));		
		System.out.println("Résultat = "+ m.MR()); */
				
		Window w = new Window ();
		calcListener c = new calcListener(w,m);
		
	}
}
