
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

public class Ts {
   public static void main(String[] args) throws IOException {
	 UrlResource ur = new UrlResource("file:src/main/resources/log4j.properties");
	 System.out.println(ur.getFilename());
	 Properties p = new Properties();
	 p.load(ur.getInputStream());
	 String property = p.getProperty("log4j.rootLogger");
	 System.out.println(property);
	 ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	 Resource[] resources = resolver.getResources("classpath*:mybatis/mappers/*/*.xml");
      for (Resource resource : resources) {
		System.out.println(resource.getFilename());
	} 
      String name = WebApplicationContext.class.getName();
      System.out.println(name);
     // WebApplicationContextUtils.getWebApplicationContext(sc);
      
   }
  
}
