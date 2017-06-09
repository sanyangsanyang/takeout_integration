package nju.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nju.dao.QueryDao;
import nju.model.CinemaInfo;
import nju.model.MovieInfo;
import nju.model.PlatformInfo;
import nju.type.Platform;

@Repository
public class QueryDaoImpl implements QueryDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if(session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}


	@Override
	public String[] getFiveFilms() {
		// TODO Auto-generated method stub
		
		
		return new String[] {"123","234","345","3455","352"};
	}
	
	@Override
	public String[] searchFilmsByName(String filmName) {
		
		return new String[] {"结果1","结果2","结果3"};
	}


	@Override
	public MovieInfo[] searchMovieInfo(String fileName) {
		// TODO Auto-generated method stub
		
		MovieInfo movie1= new MovieInfo();
		movie1.setMovie_name("电影名");
		movie1.setPlatform(Platform.MAO_YAN);
		movie1.setTag("鬼畜");
		movie1.setScore("10分");
		
		MovieInfo movie2= new MovieInfo();
		movie2.setMovie_name("电影名");
		movie2.setPlatform(Platform.NUO_MI);
		movie2.setTag("游戏");
		movie2.setScore("5分");
		
		MovieInfo movie3= new MovieInfo();
		movie3.setMovie_name("电影名");
		movie3.setPlatform(Platform.TIME_NET);
		movie3.setTag("动漫");
		movie3.setScore("7分");
		
		return new MovieInfo[]{
			movie1, movie2, movie3
		};
	}




	@Override
	public PlatformInfo[] searchPlatformInfo(String filmName) {
		// TODO Auto-generated method stub
		
		CinemaInfo cinemaInfo1 = new CinemaInfo();
		cinemaInfo1.setAddress("南门");
		cinemaInfo1.setCinema_name("南门电影院");
		
		CinemaInfo cinemaInfo2 = new CinemaInfo();
		cinemaInfo2.setAddress("北门");
		cinemaInfo2.setCinema_name("北门电影院");
		
		PlatformInfo info1 = new PlatformInfo();
		info1.setPlatform(Platform.MAO_YAN);
		info1.setPrice("100");
		info1.setCinema_info(cinemaInfo1);
		
		PlatformInfo info2 = new PlatformInfo();
		info2.setPlatform(Platform.NUO_MI);
		info2.setPrice("200");
		info2.setCinema_info(cinemaInfo1);
		
		PlatformInfo info3 = new PlatformInfo();
		info3.setPlatform(Platform.TIME_NET);
		info3.setPrice("300");
		info3.setCinema_info(cinemaInfo1);
		
		PlatformInfo info4 = new PlatformInfo();
		info4.setPlatform(Platform.MAO_YAN);
		info4.setPrice("50");
		info4.setCinema_info(cinemaInfo2);
		
		PlatformInfo info5 = new PlatformInfo();
		info5.setPlatform(Platform.NUO_MI);
		info5.setPrice("150");
		info5.setCinema_info(cinemaInfo2);
		
		PlatformInfo info6 = new PlatformInfo();
		info6.setPlatform(Platform.TIME_NET);
		info6.setPrice("250");
		info6.setCinema_info(cinemaInfo2);
		
		return new PlatformInfo[]{info1, info2, info3, info4, info5, info6};
	}



	
}
