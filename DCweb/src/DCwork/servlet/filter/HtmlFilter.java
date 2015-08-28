/**
 * 
 */
package DCwork.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import DCwork.servlet.entity.DcUser;

/**
 * @author Forrest
 *
 */
@WebFilter(filterName="HtmlGenerate", urlPatterns={"*.html"})
public class HtmlFilter implements Filter {

  private static Logger log = Logger.getLogger(HtmlFilter.class);
  
  @Override
  public void destroy() {
    
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse resp,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest)request;
    
    String url = req.getRequestURL().toString();
    log.info("requset url: " + url);
    
    // judge if user exists
    DcUser dcUser = (DcUser)req.getSession().getAttribute(DcUser.DC_USER);
    if(dcUser == null){
      req.getRequestDispatcher("error.jsp").forward(req, resp);
      return;
    }
    
    if(!dcUser.isPreview()){
      // forward target file
      req.getRequestDispatcher("error.jsp").forward(req, resp);
      return;
    }   
    chain.doFilter(req, resp);
    
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    
  }

}
