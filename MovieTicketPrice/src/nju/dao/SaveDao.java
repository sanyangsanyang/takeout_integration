package nju.dao;

import nju.model.CinemaInfo;
import nju.model.MovieInfo;
import nju.model.PlatformInfo;

public interface SaveDao {
	public void updateMovieInfo(MovieInfo movieInfo);
	public void updateCinemaInfo(CinemaInfo cinemaInfo);
	
	//
	public void updateBuyInfo(PlatformInfo platform, String movieName, String cinemaName);

}
