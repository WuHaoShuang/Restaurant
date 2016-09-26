package hct.restaurant.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter
{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        res.setHeader("Pragma", "No-cache");//禁止缓存
        res.setHeader("Cache-Control","no-cache");
        res.setHeader("Expires", "0");
        PrintWriter out=res.getWriter();
        HttpSession session=req.getSession();
        if(session.getAttribute("login")!=null&&session.getAttribute("restname")!=null){
            chain.doFilter(request, response);
        }
        else{
               out.println("<script>alert('您没有登录，请先登录！');this.location.replace('login.html'); </script>");
        }
    }
		
	

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
