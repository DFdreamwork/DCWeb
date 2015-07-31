/**
 * 
 */
package DCwork.servlet.listener;

import java.io.File;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import DCwork.servlet.entity.DcUser;
import DCwork.utils.PathUtils;

/**
 * @author Forrest
 *
 */
public class DCworkSesseionListener implements HttpSessionListener{

  private static Logger log = Logger.getLogger(DCworkSesseionListener.class);
  
  @Override
  public void sessionCreated(HttpSessionEvent event) {
    
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent event) {
    DcUser dcUser = (DcUser)event.getSession().getAttribute(DcUser.DC_USER);
    String siteName = dcUser.getSiteName();
    log.info("session timeout, delete related site: " + siteName);
    PathUtils.deleteDirectory(new File(PathUtils.getProjRootPath() + "/" + siteName));
  }

}
