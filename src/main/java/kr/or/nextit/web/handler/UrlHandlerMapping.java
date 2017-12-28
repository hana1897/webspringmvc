package kr.or.nextit.web.handler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import kr.or.nextit.web.servlet.Controller;

public class UrlHandlerMapping {
	
	public static Map<String, Controller> handlerMap = new HashMap<>(); 
	
	private UrlHandlerMapping() {}
	
	public static void init(String configFilePath) throws Exception {
		
		Properties prop = new Properties();
		prop.load(new FileReader(configFilePath));
		
		 Iterator keyItr = prop.keySet().iterator();
		 
		 while (keyItr.hasNext()) {
			String uri = (String)keyItr.next();
			String handlerClassName = prop.getProperty(uri);
			
			Class handlerClass = Class.forName(handlerClassName);
			
			Controller controller = (Controller)handlerClass.newInstance();
			
			handlerMap.put(uri, controller);
		}
		
	}
	
	public static Controller getHandler(String uri) {
		return handlerMap.get(uri);
	}
	
	
}
