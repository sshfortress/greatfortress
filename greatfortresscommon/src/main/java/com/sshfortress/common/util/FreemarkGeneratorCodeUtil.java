package com.sshfortress.common.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;




import com.sshfortress.common.beans.DabaseTable;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkGeneratorCodeUtil {
	
  public static final Map<String,String> mapType = new HashMap<String,String>();
  public static void GeneratorEntity(List<Map<String, String>> list,DabaseTable dabaseTable) throws IOException, TemplateException{
	  
	  
	  Configuration cfg = new Configuration();  
      cfg.setDirectoryForTemplateLoading(new File(  
              "C:/Users/李俊/workspace/kingee/src/main/resources/template/"));  
      cfg.setObjectWrapper(new DefaultObjectWrapper()); 
      cfg.setDefaultEncoding("UTF-8");
      
      String className =tableToJava(dabaseTable.getTableName(), "");
      
      
      Template temp = cfg.getTemplate("Entity.java.ftl");  
      
      Map<String, Object> root = new HashMap<String, Object>();  
      root.put("class", className);  
      Collection<Map<String, String>> columns = new HashSet<Map<String, String>>();  
      root.put("columns", columns);  
      for (Map<String, String> map : list) {
          Map<String, String> column = new HashMap<String, String>(); 
          column.put("name",tableToJava(map.get("columnName"),""));  
          column.put("type",mapType.get(map.get("dataType")));  
          column.put("comments", map.get("columnComment"));  
          columns.add(column);  
	   }
      
      PrintWriter writer   = new PrintWriter("E:/"+className+".java"); 
      temp.process(root, writer);  
      writer.flush();  
 
  }
  public static void GeneratorDao(List<DabaseTable> list) throws IOException, TemplateException {
	
	  
  }
  public static void GeneratorService(List<DabaseTable> list) {
	  
  }
  public static void GeneratorController(List<DabaseTable> list) {
	  
  }
  
  
  
  /**
	 * 列名转换成Java属�?�名
	 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
	}

	/**
	 * 表名转换成Java类名
	 */
	public static String tableToJava(String tableName, String tablePrefix) {
		if (StringUtils.isNotBlank(tablePrefix)) {
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName);
	}
	public static void main(String[] args) {
		System.out.println(tableToJava("upermissionsys", ""));
		System.out.println(StringUtils.uncapitalize("INNODB_CMP_PER_INDEX_RESET"));
	}
	
	static{
		mapType.put("tinyint", "Integer");
		mapType.put("smallint", "Integer");
		mapType.put("mediumint", "Integer");
		mapType.put("int", "Integer");
		mapType.put("integer", "Integer");
		mapType.put("bigint", "Long");
		mapType.put("float", "Float");
		mapType.put("double", "Double");
		mapType.put("decimal", "BigDecimal");
		mapType.put("char", "String");
		mapType.put("varchar", "String");
		mapType.put("tinytext", "String");
		mapType.put("text", "String");
		mapType.put("mediumtext", "String");
		mapType.put("longtext", "String");
		mapType.put("date", "Date");
		mapType.put("datetime", "Date");
		mapType.put("timestamp", "Date");
	}
}
