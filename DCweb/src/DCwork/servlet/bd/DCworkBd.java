/**
 * 
 */
package DCwork.servlet.bd;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import DCwork.servlet.entity.DcUser;

/**
 * @author Forrest
 *
 */
public abstract class DCworkBd {

  private Map<String, String[]> paraMap = new HashMap<String, String[]>();
  private DcUser user = null;
  
  public DCworkBd(HttpServletRequest req) throws Exception{
    initializeBd(req);
    doAfterInitialize();
  }

  private void initializeBd(HttpServletRequest req) throws Exception {
    paraMap = req.getParameterMap();
    
    if(shouldCreateDefaultUser()){
      setUser(DcUser.createDcUser("classic", "classic"));
      req.getSession().setAttribute(DcUser.DC_USER, getUser());
    }else{
      setUser((DcUser)req.getSession().getAttribute(DcUser.DC_USER));
    }
    if(needCheckFuncRights() && getUser() == null){
      throw new Exception("No Funcion Rights");
    }
  }

  public abstract boolean needCheckFuncRights();

  public abstract boolean shouldCreateDefaultUser();

  public abstract void doAfterInitialize() throws Exception;
  
  public DcUser getUser() {
    return user;
  }

  public void setUser(DcUser user) {
    this.user = user;
  }
  
  public String getParamete(String paraName){
    if(paraMap.containsKey(paraName)){
      return paraMap.get(paraName)[0];
    }
    return StringUtils.EMPTY;
  }
  
  public String[] getParametes(String paraName){
    return paraMap.get(paraName);
  }
  
}
