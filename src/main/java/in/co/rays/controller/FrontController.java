package in.co.rays.controller;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.util.ServletUtility;

/**
 * FrontCtl performs the session checking
 * and logging operations before calling any other application controller
 * It's also prevents user from access application without login.
 * 
 * @author Aditya
 * @since 2025
 * @version 1.0
 */
@WebFilter(urlPatterns = { "/doc/*", "/ctl/*" })
public class FrontController implements Filter {

    /**
     * Initializes the filter.
     * 
     * @param filterConfig configuration for this filter
     * @throws ServletException if initialization fails
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Performs filtering by checking session validity. If user is not 
     * logged in, request is forwarded to login page.
     * 
     * @param req   ServletRequest
     * @param resp  ServletResponse
     * @param chain FilterChain
     * @throws IOException      if I/O error occurs
     * @throws ServletException if servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("in do filter");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        String uri = request.getRequestURI();
        request.setAttribute("uri", uri);

        if (session.getAttribute("user") == null) {
            request.setAttribute("error", "Your session has been expired. Please login again..!!!");
            ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
            return;

        } else {
            chain.doFilter(request, response);
        }

    }

    /**
     * Cleans up resources before filter destruction.
     */
    @Override
    public void destroy() {
    }

}
