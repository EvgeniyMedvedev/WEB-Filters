package servlet.filter;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = "/")
public class FilterServlet implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        resp.setContentType("text/html;charset=utf-8");

        UserService service = UserService.getInstance();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        String loginSession = (String) session.getAttribute("login");
        String passwordSession = (String) session.getAttribute("password");

        //Logged user.
        if (loginSession != null && passwordSession != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (login != null && password != null) {
            if (service.validateUser(login, password)) {
                String role = service.getRole(login, password);

                session.setAttribute("password", password);
                session.setAttribute("login", login);
                session.setAttribute("role", role);

                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
        }
    }
}
