package nju.action;

import java.io.IOException;
import java.io.Writer;
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

import nju.service.InfoService;


public class FilmAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, ServletContextAware{

	
	private ServletContext servletContext;
	private HttpServletRequest servletRequest;
	private HttpServletResponse servletResponse;
	private Map sessionMap;
	
	@Autowired
    private InfoService info;
	
	public FilmAction() {
		super();
	}
	
	public String execute(){
		String type = servletRequest.getParameter("type");
		servletResponse.setContentType("text/html");
		servletResponse.setCharacterEncoding("UTF-8");
		
		if(type.equals("hotfilm")) {
			try {
				Writer w = servletResponse.getWriter();
				w.write(info.getHotFilms());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		} else if(type.equals("films")) {
			String name = servletRequest.getParameter("name");
			try {
				Writer w = servletResponse.getWriter();
				w.write(info.searchFilms(name));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
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
