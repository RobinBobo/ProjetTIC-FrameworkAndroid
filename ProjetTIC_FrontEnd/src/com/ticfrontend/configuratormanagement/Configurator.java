package com.ticfrontend.configuratormanagement;

public class Configurator {

	//Class'Attributes
	private boolean shoppingCart;

	private boolean customerNotice;

	private boolean order;

	private boolean sortProduct;	

	private boolean sortCategory;

	private boolean categorySearch;

	private boolean productSearch;

	private String websiteName;

	private int buttonsColor;

	public Configurator () {
		this.shoppingCart = false;
		this.customerNotice = false;
		this.order = false;
		this.categorySearch = false;
		this.productSearch = false;
		this.sortCategory = false;
		this.sortProduct = false;
		this.websiteName = "";
	}

	public int getButtonsColor() {
		return buttonsColor;
	}

	public void setButtonsColor(int buttonColor) {
		this.buttonsColor = buttonColor;
	}

	//Getter & Setter
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

	public boolean getSortProduct() {
		return sortProduct;
	}

	public void setSortProduct(boolean sortProduct) {
		this.sortProduct = sortProduct;
	}

	public boolean getSortCategory() {
		return sortCategory;
	}

	public void setSortCategory(boolean sortCategory) {
		this.sortCategory = sortCategory;
	}

	public boolean getCategorySearch() {
		return categorySearch;
	}

	public void setCategorySearch(boolean categorySearch) {
		this.categorySearch = categorySearch;
	}

	public boolean getProductSearch() {
		return productSearch;
	}

	public void setProductSearch(boolean productSearch) {
		this.productSearch = productSearch;
	}
}
