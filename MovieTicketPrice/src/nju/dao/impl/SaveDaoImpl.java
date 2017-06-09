package nju.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nju.dao.SaveDao;
import nju.model.CinemaInfo;
import nju.model.MovieInfo;
import nju.model.PlatformInfo;

@Repository
public class SaveDaoImpl implements SaveDao{
	
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
	public void updateMovieInfo(MovieInfo movieInfo) {
		// TODO Auto-generated method stub
		//更新电影
	}

	@Override
	public void updateCinemaInfo(CinemaInfo cinemaInfo) {
		// TODO Auto-generated method stub
		//影院数据
	}


	@Override
	public void updateBuyInfo(PlatformInfo platform, String movieName, String cinemaName) {
		// TODO Auto-generated method stub
		
	}

}
