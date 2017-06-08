package nju.model;

import java.util.HashSet;
import java.util.Set;

public class CinemaInfo {

	private int cinema_id;
	private String address;
	private String cinema_name;
	private Set<PlatformInfo> infos = new HashSet<PlatformInfo>();
	
	public Set<PlatformInfo> getInfos() {
		return infos;
	}
	public void setInfos(Set<PlatformInfo> infos) {
		this.infos = infos;
	}
	public int getCinema_id() {
		return cinema_id;
	}
	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCinema_name() {
		return cinema_name;
	}
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
}
