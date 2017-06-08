package nju.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nju.dao.QueryDao;
import nju.model.CinemaInfo;

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
	public CinemaInfo test(String name) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Query<CinemaInfo> query = session.createQuery("from CinemaInfo where cinema_name='"+ name + "'");
        List<CinemaInfo> list = query.list();
		if(list.isEmpty())
			return null;
		
		CinemaInfo info = list.get(0);

		return info;
	}
	
}
