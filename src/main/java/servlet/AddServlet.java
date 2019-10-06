package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add")
public class AddServlet extends HttpServlet {

    UserService service = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        if (firstName != null || lastName != null || !firstName.isEmpty() || !lastName.isEmpty()) {
            service.addUser(new User(firstName, lastName));
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }

}
