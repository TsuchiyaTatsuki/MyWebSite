package beans;

import java.io.Serializable;
import java.text.NumberFormat;

public class MyItemDataBeans implements Serializable {
	private int id;
	private String name;
	private int gender;
	private String category;
	private int categoryId;
	private String detail;
	private int price;
	private String fileName;

	public int getId() {
		return id;
	}

	public void setId(int itemId) {
		this.id = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String itemName) {
		this.name = itemName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int itemPrice) {
		this.price = itemPrice;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String filename) {
		this.fileName = filename;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFormatPrice() {
		NumberFormat nfCur = NumberFormat.getCurrencyInstance();
		return nfCur.format(price);
	}

	public String getGenderName() {
		String genderName = gender == 0 ? "メンズ": "レディース";
		return genderName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


}
