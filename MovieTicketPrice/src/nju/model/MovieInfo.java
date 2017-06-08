package nju.model;

import java.util.HashSet;
import java.util.Set;

public class MovieInfo {

	private int movie_id;
	private String movie_name;
	private String info;
	private String img_path;
	private Set<PlatformInfo> infos = new HashSet<PlatformInfo>();;
	
	public Set<PlatformInfo> getInfos() {
		return infos;
	}
	public void setInfos(Set<PlatformInfo> infos) {
		this.infos = infos;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
}
