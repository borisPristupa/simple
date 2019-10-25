import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class OnlyControllerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest &&
                ((HttpServletRequest) servletRequest).getServletPath().equals("/Controller")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletRequest.getRequestDispatcher("/index.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
