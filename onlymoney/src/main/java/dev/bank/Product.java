package dev.bank;

public class Product {
	private String productCode;
	private String name;
	private int cost;
	private int count = 0;
	
	public Product(String productCode, String name, int cost) {
		super();
		this.productCode = productCode;
		this.name = name;
		this.cost = cost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

}
