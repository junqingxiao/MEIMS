package filter.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author mk
 */
public class LoginFilter implements javax.servlet.Filter
{
    public final void destroy(){};

    public final void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException,ServletException
    {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpSession session=request.getSession();
        if (session.getAttribute("name") == null)
        {
              request.getRequestDispatcher("login.jsp").forward(request,res);
        }
        else
        {
            chain.doFilter(req,res);
        }
    }

    public void init(FilterConfig aeg0)throws ServletException{}
}





















