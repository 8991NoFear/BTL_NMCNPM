package model;

import java.util.LinkedList;

import bean.Product;

public class Cart {
	private LinkedList<Product> listProduct;
	
	public Cart() {
		listProduct = new LinkedList<Product>();
	}
	
	public LinkedList<Product> getListProduct() {
		return listProduct;
	}
	
	public void setListProduct(LinkedList<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public int getTotalMoney() {
		int totalMoney = 0;
		for(Product product: listProduct) {
			totalMoney += product.getPrice()*product.getQuantity();
		}
		return totalMoney;
	}
	
	public int getAmountProduct() {
		int amount = 0;
		for(Product product: listProduct) {
			amount += product.getQuantity();
		}
		return amount;
	}
	
	public Product findProduct(int productID) {
		for(Product product: listProduct) {
			if(product.getProductID() == productID) {
				return product;
			}
		}
		return null;
	}
}
