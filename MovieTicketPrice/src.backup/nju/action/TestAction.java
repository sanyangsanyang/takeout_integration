package nju.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import nju.model.CinemaInfo;
import nju.service.TestService;


public class TestAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware{

	
	private ServletContext servletContext;
	private HttpServletRequest servletRequest;
	private HttpServletResponse servletResponse;
	private Map sessionMap;
	
	@Autowired
    private TestService test;
	
	public TestAction() {
		super();
	}
	
	public String execute(){
		CinemaInfo a = test.test("danian");
		servletResponse.setContentType("text/html");
		return "success";
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public void setSession(Map map) {
		this.sessionMap = map;
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
