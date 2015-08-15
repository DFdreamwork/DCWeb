/**
 * 
 */
package DCwork.servlet.bd;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @author Forrest
 *
 */
public class ResourceUpdatedBd extends DCworkBd{

  public ResourceUpdatedBd(HttpServletRequest req) throws Exception {
    super(req);
  }

  @Override
  public boolean needCheckFuncRights() {
    return true;
  }

  @Override
  public boolean shouldCreateDefaultUser() {
    // TODO For Testing now
    return true;
  }

  @Override
  public void doAfterInitialize() throws Exception {
    // TODO load resource and template
  }
  
  public String getResourceAsHtml(){
    StringBuilder sb = new StringBuilder();
    sb.append("<select name=\"RES_ITEM\" >");
    sb.append("<option value=\"\"></option>");    
    List<String> list = getUser().getResLoader().getResourceFiles();
    for(String l : list){
      String desc = l.substring(1 , l.lastIndexOf('_') );
      String selected = desc.equals(getParamete("RES_ITEM")) ? "selected" : StringUtils.EMPTY ;
      sb.append(String.format("<option value=\"%s\" %s >%s</option>", desc, selected, desc ));
      
    }
    sb.append("</select>");
    
    return sb.toString();
  }
  
  public String showResoure(){
    StringBuilder sb = new StringBuilder();
    String resItem = getParamete("RES_ITEM");
    if(StringUtils.isNotEmpty(resItem) ){
      Map<String, String> resMap = getUser().getResource(resItem);
      if(resMap == null){
        getUser().getResLoader().loadFileResourceMap(resItem);
        getUser().addResource(resItem, resMap);
      }
      
      for(Entry entry : resMap.entrySet()){
        sb.append(entry.getKey() + ":" + entry.getValue() + "<br>");
      }
    }
    return sb.toString();
  }

}
