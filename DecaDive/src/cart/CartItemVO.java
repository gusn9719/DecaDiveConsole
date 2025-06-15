package cart;

public class CartItemVO {
	private int itemCode;
	private int quantity;
	
	public CartItemVO(int itemCode, int quantity) {
		this.itemCode = itemCode;
		this.quantity = quantity;
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
	
	@Override
	public String toString() {
		return " [" + itemCode + "번 상품, " + quantity + "개]";
	}
}
