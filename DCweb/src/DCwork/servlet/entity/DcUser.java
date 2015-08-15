/**
 * 
 */
package DCwork.servlet.entity;

import java.util.HashMap;
import java.util.Map;

import DCwork.servlet.resource.ResourceLoader;

/**
 * @author Forrest
 *
 */
public class DcUser {
  
  public static String DC_USER = "DC_USER";
  
   private String templateName = "";
   private String siteName = "";
   private boolean isCharge = false;
   private ResourceLoader resLoader = null;
   
   private Map<String, Map<String, String>> resMap = new HashMap<String, Map<String, String>>();
   
   private DcUser(String sName, String tName){
     setSiteName(sName);
     setTemplateName(tName);
   }
   
   public static DcUser createDcUser(String sName, String tName){
     return new DcUser(sName, tName);
   }

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
    setResLoader(new ResourceLoader(getTemplateName()));
    // TODO - create folder if it change - and site must be set before
    // if the site name is not specified, set a random one
  }

  public String getSiteName() {
    return siteName;
  }

  private void setSiteName(String siteName) {
    this.siteName = siteName;
  }

  public boolean isCharge() {
    return isCharge;
  }

  public void setCharge(boolean isCharge) {
    this.isCharge = isCharge;
  }

  public ResourceLoader getResLoader() {
    return resLoader;
  }

  public void setResLoader(ResourceLoader resLoader) {
    this.resLoader = resLoader;
  }
  
  public boolean hasResourece(String resItem){
    return resMap.containsKey(resItem);
  }
  
  public void addResource(String resItem, Map<String, String> resList){
    resMap.put(resItem, resList);
  }

  public Map<String, String> getResource(String resItem) {
    if(hasResourece(resItem)){
      return resMap.get(resItem);
    }
    return null;
  }
  
}
