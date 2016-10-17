package hct.restaurant.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * Servlet Filter implementation class MyStrutsFilter
 */
public class MyStrutsFilter extends StrutsPrepareAndExecuteFilter  {


	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getRequestURI();         
		if (url.contains("/UE/jsp/")) {                    
			chain.doFilter(req, res);         
		}else{                         
			super.doFilter(req, res, chain);         
		} 
	}


}
