package nju.vo;

public class MaoYanPriceImgMsg {
	private String path;
	private int x;
	private int y;
	
	public MaoYanPriceImgMsg() {
	}
	
	public MaoYanPriceImgMsg(String path, int x, int y) {
		this.path = path;
		this.x = x;
		this.y = y;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
