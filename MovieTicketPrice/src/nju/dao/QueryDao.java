package nju.dao;

import nju.model.CinemaInfo;
import nju.model.MovieInfo;
import nju.model.PlatformInfo;

public interface QueryDao {
	public String[] getFiveFilms();
	public String[] searchFilmsByName(String filmName);
	public MovieInfo[] searchMovieInfo(String filmName);
	public PlatformInfo[] searchPlatformInfo(String filmName);
}
