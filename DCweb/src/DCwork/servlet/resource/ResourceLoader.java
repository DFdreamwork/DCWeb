/**
 * 
 */
package DCwork.servlet.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import DCwork.utils.PathUtils;

/**
 * @author Forrest
 *
 */
public class ResourceLoader {

  private String templateName = "";
  
  public ResourceLoader(String tName){
    setTemplateName(tName);
  }

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }
  
  
  public String getResourceName(String templateFileName){
    File file = new File(PathUtils.getProjClassPath() + "/template/" + templateFileName);
    return file.getName().substring(0, file.getName().length() - 3);
  }
  
  /**
   * load template file's resource
   * 
   * @param templateFileName
   * @return
   */
  public Map<String, String> loadResourceMap(String templateFileName){
    // add common resource 
    Map<String, String> paraMap = loadFileResourceMap("site");
    paraMap.putAll(loadFileResourceMap(getResourceName(templateFileName)));
    return paraMap;
  }
  
  /**
   * Load related file resource
   * 
   * @return
   */
  private Map<String, String> loadFileResourceMap(String templateFileName){
    Map<String, String> paraMap = new HashMap<String, String>();
    String resoureName = String.format("resource/%s/%s_resource", getTemplateName(), templateFileName);
    ResourceBundle rb = ResourceBundle.getBundle(resoureName);
    Iterator<String> it = rb.keySet().iterator();
    while(it.hasNext()){
      String str = it.next();
      paraMap.put(str, rb.getString(str));
    }
    
    return paraMap;
  }
  
  public List<String> getTemplateFiles(){
    List<String> list = new ArrayList<String>();
    File file = new File(PathUtils.getProjClassPath() + "/template/" + getTemplateName());
    for(File f : PathUtils.listAllFiles(file)){
      list.add(f.getAbsolutePath().substring(f.getAbsolutePath().indexOf(getTemplateName()) + getTemplateName().length()));
    }
    return list;
  }
}
