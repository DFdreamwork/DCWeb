package DCwork.servlet.template;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DCwork.servlet.entity.DcUser;

/**
 * @author Forrest
 *
 */
public class WebGenerateServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		
		DcUser dcUser = (DcUser)req.getSession().getAttribute(DcUser.DC_USER);
		if(dcUser == null){
		  // TODO - set to the classic for Testing
		  dcUser = DcUser.createDcUser("classic", "classic");
		  req.getSession().setAttribute(DcUser.DC_USER, dcUser);
		}
		
		String targetUrl = String.format("../../jsp/resource_update.jsp");
		resp.sendRedirect(targetUrl);
	}

}
