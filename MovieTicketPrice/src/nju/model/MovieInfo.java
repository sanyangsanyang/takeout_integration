package nju.model;

import java.util.HashSet;
import java.util.Set;

import nju.type.Platform;

public class MovieInfo {

	private int movie_id;
	private String movie_name;
	private String img_url;
	private Platform platform;
	private String country;
	private String duration;
	private String score;
	private String tag;
	private Set<PlatformInfo> infos = new HashSet<PlatformInfo>();;
	
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
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Set<PlatformInfo> getInfos() {
		return infos;
	}
	public void setInfos(Set<PlatformInfo> infos) {
		this.infos = infos;
	}
	
}
