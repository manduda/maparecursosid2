/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MADD
 */
public class LoginFilter implements Filter {
    private static final String LOGIN_JSP = "/faces/index.xhtml";

    public LoginFilter() {

	}

    private static boolean checkLoginState(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        boolean isLoggedIn = false;
        
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        UserBean userSession = null;

        // If there is a UserBean in the session, and it has
        // the isLoggedIn property set to true.
        if (null != session && (null != (userSession = (UserBean) session.getAttribute("UserBean")))) { 
            if (userSession.isIsLoggedIn()) {
                isLoggedIn = true;
            }
        }
	return isLoggedIn;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean isLoggedIn = checkLoginState(request, response);
        //System.out.println("sesion: "+isLoggedIn);
	if (isRedirect((HttpServletRequest) request) && !isLoggedIn) {
            String loginURI = LOGIN_JSP;
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginURI);
            // Force the login
            requestDispatcher.forward(request, response);
            return;
	} else {
            try {
                chain.doFilter(request, response);
            } catch (Throwable t) {
                // A production quality implementation will
		// deal with this exception.
            }
        }
    }

    private boolean isRedirect(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
	return (!requestURI.contains(LOGIN_JSP));
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }

}
