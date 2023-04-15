package javafish.clients.opc.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.uWinOPCTjyx.util.*;

/**
 * Properties loader
 * <p>
 * <i>NOTE:</i>Root of properties files has to be in a class path
 */
public class PropertyLoader {

  private static final String DEFAULT = "default";
  private static final String DEFAULT_LOGGER = "log4j";

  /**
   * Get property for class (propsName)
   *
   * @param propsName class package
   * @param propertyName key
   * @return property String
   */
  public static String getProperty(String propsName, String propertyName) {
    Properties props = loadProperties(propsName);
    return props.getProperty(propertyName);
  }

  /**
   * Get default application property
   *
   * @param propertyName String
   * @return property String
   */
  public static String getDefaultProperty(String propertyName) {
    return getDefaultProperties().getProperty(propertyName);
  }

  /**
   * Get default logger properties
   *
   * @return properties Properties
   */
  public static Properties getDefaultLoggerProperties() {
    return loadProperties(getDefaultProperty(DEFAULT_LOGGER));
  }

  /**
   * Return default application properties from default.properties
   *
   * @return properties Properties
   */
  public static Properties getDefaultProperties() {
    return loadProperties(DEFAULT);
  }

  /**
   * Get properties by class (className)
   *
   * @param className Class
   * @return properties Properties
   */
  public static Properties loadProperties(Class className) {
    return loadProperties(className.getName());   //javafish.clients.opc.JCustomOpc   获取到文件全路径
  }

  /**
   * Get properties for class (propsName)
   *
   * @param propsName class package
   * @return properties Properties
   */
  public static Properties loadProperties(final String propsName) {
    Properties props = null;
    InputStream in = null;
    try {
    	ClassLoader cl = ClassLoader.getSystemClassLoader();
    	String name = propsName.replace('.', '/').concat(".properties");      // propsName    javafish/clients/opc/JCustomOpc.properties
    	System.out.println("propsName======"+propsName);
    	System.out.println("name======"+name);
    	
    	//路径问题参考链接:https://blog.csdn.net/weixin_41489022/article/details/113522628
    	Class<OpcUtil> clazz = OpcUtil.class;
    	String filePath = clazz.getResource("/"+name).getPath();
    	//clazz.getResource("/javafish/clients/opc/JCustomOpc.properties").getPath();
    	System.out.println(filePath);
    	
    	//File file = new File("D:/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/UWinOPCTjyx/WEB-INF/classes/javafish/clients/opc/JCustomOpc.properties");
    	
    	in = new FileInputStream(new File(filePath));
    	//in = cl.getResourceAsStream(name);
	    //in = cl.getResourceAsStream("configBf2.properties");
    	System.out.println("in======"+in);
        if (in != null) {
          props = new Properties();
          props.load(in);
        }
    }
    catch (Exception e) {
        props = null;
    }
    finally {
        if (props == null) {
          System.err.print("Property file " + propsName + " doesn't exist. System terminated.");
        System.exit(0);
      }
    }
    return props;
  }

  // EXAMPLE
  public static void main(String[] args) {
    System.out.println(getProperty("test", "xprop"));
    System.out.println(getProperty("nested.test1", "yprop"));
  }

}

