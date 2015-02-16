package configuratormangement;

public class Configurator {
	
	//Class'Attributes
	private boolean shoppingCart;
	
	private boolean customerNotice;
	
	private boolean order;
	
	private String websiteName;	
	
	//Getter & Setter
	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public boolean getIsCustomerNotice() {
		return customerNotice;
	}

	public void setCustomerNotice(boolean customerNotice) {
		this.customerNotice = customerNotice;
	}	

	public boolean getIsOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

	public boolean getIsShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(boolean shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
}
