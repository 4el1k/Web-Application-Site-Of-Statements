package ru.itis.controllers.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit;
import java.io.IOException;

@WebFilter("/*")
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String context = request.getContextPath();
        boolean sessionExist = session != null;
        boolean isAccessiblePage = request.getRequestURI().equals(context + "/authorisation")
                || request.getRequestURI().equals(context + "/authentication")
                || request.getRequestURI().equals(context + "/feed");
        boolean isFeed = request.getRequestURI().equals(context + "/feed"); // changed
        boolean isAuthenticate = false;

        if (sessionExist) {
            if (session.getAttribute("isAuth") != null) { // changed
                isAuthenticate = (boolean) session.getAttribute("isAuth"); // changed
            }
            if (!isAuthenticate && isAccessiblePage) {
                filterChain.doFilter(request, response);
                return;
            }
            if (!isAuthenticate && !isAccessiblePage) {
                response.sendRedirect(context + "/authorisation");
                return;
            }
            if (isAuthenticate && isAccessiblePage && !isFeed) {
                response.getWriter().println("You already logged in");
                return;
            }
            if (isAuthenticate && !isAccessiblePage) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        if (!sessionExist) {
            if (!isAuthenticate && isAccessiblePage) {
                filterChain.doFilter(request, response);
                return;
            }
            if (!isAuthenticate && !isAccessiblePage) {
                response.sendRedirect(context + "/authorisation");
                return;
            }
            if (isAuthenticate) {
                response.sendRedirect(context + "/authorisation");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
