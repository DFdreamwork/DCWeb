/**
 * 
 */
package DCwork.utils;

import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/**
 * @author Forrest
 *
 */
public class VelocityUtils {
  private static Logger log = Logger.getLogger(VelocityUtils.class);

  static VelocityEngine VELOCITY_ENGINE;

  static {
    VELOCITY_ENGINE = new VelocityEngine();
    VELOCITY_ENGINE.setProperty("resource.loader", "class");
    VELOCITY_ENGINE.setProperty("class.resource.loader.description",
        "Velocity ClassPath Resource Loader");
    VELOCITY_ENGINE.setProperty("class.resource.loader.class",
        "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

    try {
      VELOCITY_ENGINE.init();
    } catch (Exception e) {
      log.info("Error when init Velocity Engine", e);
    }

    Velocity.init();

  }

  public static void merge(VelocityContext context, String templateName,
      PrintWriter pw) {
    log.info("velocity merge template: " + templateName);
    Template t = VELOCITY_ENGINE.getTemplate(templateName);
    t.merge(context, pw);
  }

}
