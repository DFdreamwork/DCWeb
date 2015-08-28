/**
 * 
 */
package DCwork.utils;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;

import DCwork.servlet.resource.ResourceLoader;

/**
 * @author Forrest
 *
 */
public class WebGenerateUtils {

  public static void generateWeb(String siteName, ResourceLoader rLoader) throws Exception{
    
    List<String> list = rLoader.getTemplateFiles();
    for(String templateFileName : list){
      if(templateFileName.endsWith(".vm")){
        Map<String, String> paraMap = rLoader.loadResourceMap(templateFileName);
        VelocityContext context = new VelocityContext(paraMap);
        try(PrintWriter pw = getTargetPrintWriter(siteName, templateFileName)){
          VelocityUtils.merge(context, "template/" + siteName + "/" + templateFileName, pw);
        }
      }else{
        // just copy directly
        File f = new File(PathUtils.getProjClassPath() + "template/" + siteName + templateFileName);
        PathUtils.createDirecties(getTargetFile(siteName, templateFileName).getParentFile());
        Files.copy(f.toPath(),
            getTargetFile(siteName, templateFileName).toPath());
      }
      
    }
  }

  public static File getTargetFile(String siteName, String templateName){
    String fileName = PathUtils.getProjRootPath() + siteName  + "/"
        + templateName.replaceAll("\\.vm$", ".html");
    return new File(fileName);
  }
  
  public static PrintWriter getTargetPrintWriter(String siteName, String templateName) throws Exception {
    File file = getTargetFile(siteName, templateName);
    if(!file.exists()){
      PathUtils.createDirecties(file.getParentFile());
      file.createNewFile();
    }
    return new PrintWriter(file);
  }
}
