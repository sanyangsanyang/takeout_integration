package nju.vo;

public class Film {
	private String img_url;
	private String name;
	private String duration;
	
	private String time_score;
	private String maoyan_score;
	private String nuomi_score;
	
	private String time_tag;
	private String maoyan_tag;
	private String nuomi_tag;
	

	public Film(String img_url, String name, String duration, String time_score, String maoyan_score,
			String nuomi_score, String time_tag, String maoyan_tag, String nuomi_tag) {
		super();
		this.img_url = img_url;
		this.name = name;
		this.duration = duration;
		
		this.time_score = time_score;
		this.maoyan_score = maoyan_score;
		this.nuomi_score = nuomi_score;
		this.time_tag = time_tag;
		this.maoyan_tag = maoyan_tag;
		this.nuomi_tag = nuomi_tag;
	}
	
	public Film(String img_url, String name, String duration) {
		this.img_url = img_url;
		this.name = name;
		this.duration = duration;
		
		this.time_score = "0";
		this.maoyan_score = "0";
		this.nuomi_score = "0";
		this.time_tag = "";
		this.maoyan_tag = "";
		this.nuomi_tag = "";
	}
	
	public String getImg_url() {
		return img_url;
	}


	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}

	
	public String getTime_score() {
		return time_score;
	}

	public void setTime_score(String time_score) {
		this.time_score = time_score;
	}

	public String getMaoyan_score() {
		return maoyan_score;
	}

	public void setMaoyan_score(String maoyan_score) {
		this.maoyan_score = maoyan_score;
	}

	public String getNuomi_score() {
		return nuomi_score;
	}

	public void setNuomi_score(String nuomi_score) {
		this.nuomi_score = nuomi_score;
	}

	public String getTime_tag() {
		return time_tag;
	}

	public void setTime_tag(String time_tag) {
		this.time_tag = time_tag;
	}

	public String getMaoyan_tag() {
		return maoyan_tag;
	}

	public void setMaoyan_tag(String maoyan_tag) {
		this.maoyan_tag = maoyan_tag;
	}

	public String getNuomi_tag() {
		return nuomi_tag;
	}

	public void setNuomi_tag(String nuomi_tag) {
		this.nuomi_tag = nuomi_tag;
	}

}

