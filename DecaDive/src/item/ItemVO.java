package item;

import java.io.Serializable;
import java.util.Date;

public class ItemVO implements Serializable {
	private int itemCode;
	private String decade; // 80s, 90s, 00s
	private String category; // "WALL" | "DESK" | "TECH" | "COMICS" | "FASHION"
	private String name;
	private int price;
	private int instock;
	private Date regdate;

	public ItemVO(int itemCode, String decade, String category, String name, int price, int instock, Date regdate) {
		this.itemCode = itemCode;
		this.decade = decade;
		this.category = category;
		this.name = name;
		this.price = price;
		this.instock = instock;
		this.regdate = regdate;
	}

	public ItemVO(String decade, String category, String name, int price, int instock) {
		this(-1, decade, category, name, price, instock, null);
	}

	@Override
	public String toString() {
		return "[" + itemCode + ", " + decade + ", " + category + ", " + name + ", " + price + ", " + instock + "]";
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public String getDecade() {
		return decade;
	}

	public void setDecade(String decade) {
		this.decade = decade;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getInstock() {
		return instock;
	}

	public void setInstock(int instock) {
		this.instock = instock;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
