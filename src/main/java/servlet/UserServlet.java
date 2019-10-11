package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute("login");
        String password = (String) req.getSession().getAttribute("password");
        UserService service = UserService.getInstance();
        User user = service.getUserByLoginAndPassword(login,password);
        req.setAttribute("user",user);
        req.getRequestDispatcher("/WEB-INF/view/user.jsp").forward(req, resp);
    }
}
