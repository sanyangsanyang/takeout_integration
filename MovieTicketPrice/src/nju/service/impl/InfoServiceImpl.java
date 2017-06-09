package nju.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import nju.dao.QueryDao;
import nju.model.CinemaInfo;
import nju.model.MovieInfo;
import nju.model.PlatformInfo;
import nju.service.InfoService;
import nju.vo.Cinema;
import nju.vo.Film;

@Service
public class InfoServiceImpl implements InfoService{

	@Autowired
	private QueryDao dao;
	

	@Override
	public String getHotFilms() {
		// TODO Auto-generated method stub	
		return (new Gson().toJson(dao.getFiveFilms()));
	}

	@Override
	public String searchFilms(String filmName) {
		// TODO Auto-generated method stub
		return (new Gson().toJson(dao.searchFilmsByName(filmName)));
	}

	@Override
	public String searchFilm(String filmName) {
		// TODO Auto-generated method stub
		MovieInfo[] infos = dao.searchMovieInfo(filmName);
		Film film = new Film(infos[0].getImg_url(), infos[0].getMovie_name(), infos[0].getDuration());
		for(MovieInfo info : infos) {
			switch (info.getPlatform()) {
			case MAO_YAN:
				film.setMaoyan_score(info.getScore());
				film.setMaoyan_tag(info.getTag());
				break;
			case TIME_NET:
				film.setTime_score(info.getScore());
				film.setTime_tag(info.getTag());
				break;
			case NUO_MI:
				film.setNuomi_score(info.getScore());
				film.setNuomi_tag(info.getTag());
				break;
			}
		}
		
		return (new Gson().toJson(film));
	}

	@Override
	public String getPriceInfo(String filmName) {
		// TODO Auto-generated method stub
		
		PlatformInfo[] platformInfos = dao.searchPlatformInfo(filmName);
		Map<String, Cinema> cinemas = new HashMap<String, Cinema>();
		
		for(PlatformInfo platformInfo : platformInfos) {
			Cinema existCinema = cinemas.get(platformInfo.getCinema_info().getCinema_name());
			if(existCinema == null) {
				//
				CinemaInfo info = platformInfo.getCinema_info();
				existCinema = new Cinema(info.getCinema_name(), info.getAddress());
				cinemas.put(info.getCinema_name(), existCinema);
			} 
			
			switch(platformInfo.getPlatform()) {
			case MAO_YAN:
				existCinema.setMaoyan_price(platformInfo.getPrice());
				break;
			case NUO_MI:
				existCinema.setNuomi_price(platformInfo.getPrice());
				break;
			case TIME_NET:
				existCinema.setTime_price(platformInfo.getPrice());
				break;
			}
		}
		
	
		return (new Gson().toJson(cinemas.values().toArray()));
	}

	public static void main(String[] args) {
//		InfoServiceImpl i = new InfoServiceImpl();
//		System.out.println(i.getHotFilms());
//		System.out.println(i.searchFilms(""));
//		System.out.println(i.searchFilm(""));
//		System.out.println(i.getPriceInfo(""));
	}
	
}
