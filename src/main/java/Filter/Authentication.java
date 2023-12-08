package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.constant;

/**
 * Servlet Filter implementation class Authentication
 */
@WebFilter(urlPatterns = { "/Authentication" }, servletNames = {

})
public class Authentication implements Filter {

	public Authentication() {

	}

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AUTHENTICATIONFILTER INITIALIZE");
	}

	public void destroy() {
		this.context.log("DESTROY AUTHENTICATION FILTER");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String queryP = req.getQueryString();
		String uri = req.getRequestURI();
		System.out.println("AUTHENTICATION FILTER");
		if (queryP != null) 
			uri += "?" + queryP;
		HttpSession session = req.getSession(false);
		if (session == null || (session.getAttribute(constant.ESession.MEMBERID.name()) == null)) 
			res.sendRedirect("LoginController?url=" + uri);
		else
			chain.doFilter(request, response);
	}

}
