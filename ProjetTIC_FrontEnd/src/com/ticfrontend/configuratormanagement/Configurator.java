package com.ticfrontend.configuratormanagement;

public class Configurator {

	//Class'Attributes
	private boolean shoppingCart;

	private boolean customerNotice;

	private boolean order;

	private String websiteName;

	private int buttonsColor;

	//Getter & Setter
	
	public int getButtonsColor() {
		return buttonsColor;
	}
	
	public void setButtonsColor(int buttonColor) {
		this.buttonsColor = buttonColor;
	}
	
	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public boolean getCustomerNotice() {
		return customerNotice;
	}

	public void setCustomerNotice(boolean customerNotice) {
		this.customerNotice = customerNotice;
	}	

	public boolean getOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

	public boolean getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(boolean shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
}
