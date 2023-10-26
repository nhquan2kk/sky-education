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

/**
 * Servlet Filter implementation class Authentication
 */
@WebFilter(
		urlPatterns = { "/Authentication" }, 
		servletNames = { 
				"AdminHomeController",
				"AdminCreateGrammarController", 
				"AdminExaminationController", 
				"AdminCreateVocabularyController"
		})
public class Authentication implements Filter {

    /**
     * Default constructor. 
     */
    public Authentication() {
       
    }
    private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Salaskjdf");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		this.context.log("Requested Resource::"+uri);
		
		HttpSession session = req.getSession(false);
		System.out.println("Salaskjdf"+uri+" url " + session + " start ");
//		System.out.print(session.getAttribute("sessionMemberId")+"memmber Id : "+session.getAttribute("sessionUser"));
		if(session == null || (session.getAttribute("sessionMemberId") == null && session.getAttribute("sessionUser") == null)){
			this.context.log("Unauthorized access request");
			res.sendRedirect("LoginController");
		}else{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}

	}


}
