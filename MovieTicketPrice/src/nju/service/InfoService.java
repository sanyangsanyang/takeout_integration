package nju.service;

import nju.vo.Cinema;
import nju.vo.Film;

public interface InfoService {
	public String getHotFilms();
	public String searchFilms(String filmName);
	public String searchFilm(String filmName);
	public String getPriceInfo(String filmName);
}
