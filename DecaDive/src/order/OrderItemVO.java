package order;

import java.io.Serializable;

public class OrderItemVO implements Serializable {
	private int itemCode;
	private int quantity;
	private int price;

	public OrderItemVO(int itemCode, int quantity, int price) {
		this.itemCode = itemCode;
		this.quantity = quantity;
		this.price = price;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "\t***" + itemCode + ", (" + quantity + "개) , (" + price + "원)";
	}
}
