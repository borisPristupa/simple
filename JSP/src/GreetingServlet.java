import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GreetingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String politeness = (String) req.getParameter("politeness");
        String greeting;
        switch (politeness) {
            case "informal":
                greeting = "Yo, ";
                break;
            case "normal":
                greeting = "Hi, ";
                break;
            case "polite":
                greeting = "Hello, ";
                break;
            default:
                req.setAttribute("error", String.format("Wrong politeness mode: '%s'", politeness));
                req.getRequestDispatcher("error_page.jsp").forward(req, resp);
                return;
        }

        req.getSession().setAttribute("greeting", greeting);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
