package beans;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBuyDataBeans  implements Serializable {
	private int id;
	private int userId;
	private int totalPrice;
	private int delivertMethodId;
	private Date buyDate;

	private String deliveryMethodName;
	private int deliveryMethodPrice;

	public MyBuyDataBeans() {

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getDelivertMethodId() {
		return delivertMethodId;
	}
	public void setDelivertMethodId(int delivertMethodId) {
		this.delivertMethodId = delivertMethodId;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public String getDeliveryMethodName() {
		return deliveryMethodName;
	}
	public void setDeliveryMethodName(String deliveryMethodName) {
		this.deliveryMethodName = deliveryMethodName;
	}

	public String getFormatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(buyDate);
	}
	public int getDeliveryMethodPrice() {
		return deliveryMethodPrice;
	}
	public void setDeliveryMethodPrice(int deliveryMethodPrice) {
		this.deliveryMethodPrice = deliveryMethodPrice;
	}
	public String getFormatPrice() {
		NumberFormat nfCur = NumberFormat.getCurrencyInstance();
		return nfCur.format(totalPrice);
	}
	public String getFormatDeliveryMethodPrice() {
		NumberFormat nfCur = NumberFormat.getCurrencyInstance();
		return nfCur.format(deliveryMethodPrice);
	}

}
