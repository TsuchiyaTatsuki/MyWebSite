package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDataBeans implements Serializable {

	private String name;
	private String address;
	private String loginId;
	private String password;
	private Date birthDate;
	private int id;

	public UserDataBeans() {
		this.name = "";
		this.address = "";
		this.loginId = "";
		this.password = "";
		this.birthDate = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(birthDate);
	}
	public void setUpdateUserDataBeansInfo(String name, String loginId, String address, Date birthDate, int id) {
		this.name = name;
		this.loginId = loginId;
		this.address = address;
		this.birthDate = birthDate;
		this.id = id;
	}

}

