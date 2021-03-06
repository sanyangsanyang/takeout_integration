package nju.model;

import java.sql.Date;
import java.sql.Timestamp;

import nju.type.Platform;

public class PlatformInfo {

	private int id;
	private double price;
	private Date date;

	private Platform platform;
	private String grade;
	private MovieInfo movie_info;
	private CinemaInfo cinema_info;
	
	public MovieInfo getMovie_info() {
		return movie_info;
	}
	public void setMovie_info(MovieInfo movie_info) {
		this.movie_info = movie_info;
	}
	public CinemaInfo getCinema_info() {
		return cinema_info;
	}
	public void setCinema_info(CinemaInfo cinema_info) {
		this.cinema_info = cinema_info;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

}
