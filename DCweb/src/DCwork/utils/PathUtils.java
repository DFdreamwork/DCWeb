/**
 * 
 */
package DCwork.utils;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Forrest
 *
 */
public class PathUtils {

  public static String PATH_SEPARATE = "/";
  
  public static String getProjClassPath(){
    return PathUtils.class.getResource("/").getPath();
  }
  
  public static String getProjRootPath(){
    return  getProjClassPath() + "../../";
  }
  
  public static List<File> listAllFiles(File file){
    List<File> list = new ArrayList<File>();
    for(File f: file.listFiles()){
      if(f.isDirectory()){
        list.addAll(listAllFiles(f));
      }else{
        list.add(f);
      }
    }
    
    return list;
  }
  
  
  public static void createDirecties(File f) throws Exception{
    Files.createDirectories(f.toPath());
  }

  public static void deleteDirectory(File file) {
    for(File f: file.listFiles()){
      if(f.isDirectory()){
        deleteDirectory(f);
      }
      f.delete();
    }
    
  }
}
