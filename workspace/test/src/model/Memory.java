package model;
public class Memory {
	
	private double MS;
	
	public Memory() {
		MS=0;
	}
	
	public void MS(double newMS) {
		this.MS = newMS;		
	}
		
	public double MR() {
		return this.MS;		
	}

	public void mPlus (double x) {
		MS(MR()+x);
	}
	
	public void mLess (double x) {
		MS(MR()+x);
	}	
	
	public void MC () {
		this.MS=0;
	}
}
