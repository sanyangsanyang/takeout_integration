package nju.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nju.dao.QueryDao;
import nju.model.CinemaInfo;
import nju.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private QueryDao dao;
	
	@Override
	public CinemaInfo test(String name) {
		return dao.test(name);
	}

}
