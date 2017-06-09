package nju.vo;

public class Cinema {

	private String name;
	private String address;
	private String time_price;
	private String maoyan_price;
	private String nuomi_price;

	public Cinema(String name, String address, String time_price, String maoyan_price, String nuomi_price) {
		super();
		this.name = name;
		this.address = address;
		this.time_price = time_price;
		this.maoyan_price = maoyan_price;
		this.nuomi_price = nuomi_price;
	}
	
	public Cinema(String name, String address) {
		this.name = name;
		this.address = address;
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
	public String getTime_price() {
		return time_price;
	}
	public void setTime_price(String time_price) {
		this.time_price = time_price;
	}
	public String getMaoyan_price() {
		return maoyan_price;
	}
	public void setMaoyan_price(String maoyan_price) {
		this.maoyan_price = maoyan_price;
	}
	public String getNuomi_price() {
		return nuomi_price;
	}
	public void setNuomi_price(String nuomi_price) {
		this.nuomi_price = nuomi_price;
	}
	
}
