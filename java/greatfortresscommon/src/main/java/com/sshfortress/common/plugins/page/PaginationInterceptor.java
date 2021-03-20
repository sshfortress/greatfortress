package com.sshfortress.common.plugins.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.sshfortress.common.util.AppPager;
import com.sshfortress.common.util.PageUtils;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ReflectUtils;
import com.sshfortress.common.util.StringUtil;

/** <p class="detail">
 * 功能：mybatis 分页插件
 * </p>
 * @ClassName: PaginationInterceptor 
 * @version V1.0  
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PaginationInterceptor implements Interceptor{
	
	/**数据库方言*/  
	private static String 		dialect 	= 		""; 
	
	/**匹配的ID(正则匹配)*/  
    private static String 		pageSqlId 	= 		""; 
	
	 /** <p class="detail">
	 * 功能：
	 * </p>
	 * @param invocation
	 * @return
	 * @throws Throwable 
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation) 
	 */ 
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		if(invocation.getTarget() instanceof RoutingStatementHandler){  
            RoutingStatementHandler statementHandler = (RoutingStatementHandler)invocation.getTarget();  
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectUtils.getProperty(statementHandler, "delegate");  
            MappedStatement mappedStatement = (MappedStatement) ReflectUtils.getProperty(delegate, "mappedStatement");  
            String sqlKey = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".")+1);
            if(sqlKey.matches(pageSqlId)){ 
                BoundSql boundSql = delegate.getBoundSql();  
                Object parameterObject = boundSql.getParameterObject();
                if(parameterObject==null){  
                    throw new NullPointerException("parameterObject尚未实例化！");  
                }else{  
                	String sql = boundSql.getSql();
                	String countSql = "select count(0) from (" + sql+ ")  tmp_count"; //记录统计  
                    Connection connection = (Connection) invocation.getArgs()[0];  
                    BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),countSql,boundSql.getParameterMappings(),parameterObject);  
                    ReflectUtils.setProperty(countBS, "metaParameters", ReflectUtils.getProperty(boundSql,  "metaParameters"));
                    PreparedStatement countStmt = null;
                    ResultSet rs = null;int count = 0;
                    try {
                    	 countStmt = connection.prepareStatement(countSql); 
                    	 setParameters(countStmt,mappedStatement,countBS,parameterObject);  
                    	 rs = countStmt.executeQuery();  
                    	 count = rs.next()?rs.getInt(1):0;
					} catch (Exception e) {
						e.printStackTrace();
						throw new ExecutorException("查询总记录数sql执行错误:"+countSql);
					}finally {
						if (rs!=null) rs.close();  
	                    if (countStmt!=null)countStmt.close(); 
					}
                   
                    String pageSql = "";
                    if(parameterObject instanceof Pager){     
                        Pager page = (Pager) parameterObject; 
            			PageUtils.computePage(page, count);
            			pageSql = generatePageSql(sql,page);
                    }else if(parameterObject instanceof AppPager){
                        AppPager page = (AppPager) parameterObject; 
                        PageUtils.computeAppPage(page);
                        pageSql = generateAppPageSql(sql,page);
                    }else if(parameterObject instanceof Map){  
                    	//拥有Page参数 
                    	Map parameterMap = (Map)parameterObject;
                    	boolean hasPage = false;
                    	for(Object key :parameterMap.keySet()){
                    		Object value = parameterMap.get(key);
                    		if(value instanceof Pager){
                    			hasPage = true;
                    			Pager page  = (Pager)value;
                    			PageUtils.computePage(page, count);
                    			ReflectUtils.setProperty(parameterObject,"page", page); 
                    			pageSql = generatePageSql(sql,page);
                                break;
                    		}else if(value instanceof AppPager){
                    		    hasPage = true;
                                AppPager page  = (AppPager)value;
                                PageUtils.computeAppPage(page);
                                ReflectUtils.setProperty(parameterObject,"page", page); 
                                pageSql = generateAppPageSql(sql, page);
                                break;
                    		}
                    	}
                    	if(!hasPage){
                    		 return invocation.proceed();
                    	}
                    }else{
                    	throw new Exception("page不存在");
                    }
                      
                    ReflectUtils.setProperty(boundSql, "sql", pageSql); //将分页sql语句反射回BoundSql.  
                }  
            }  
        }  
        return invocation.proceed();  

	}

	
	 private String generatePageSql(String sql,Pager page){  
	        if(page!=null && StringUtil.isNotEmpty(dialect)){  
	            StringBuffer pageSql = new StringBuffer();  
	            if("mysql".equals(dialect)){  
	                pageSql.append(sql);  
	                pageSql.append(" limit "+page.getStartRow()+","+page.getPageSize());  
	            }else if("oracle".equals(dialect)){  
	                pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");  
	                pageSql.append(sql);  
	                pageSql.append("  ) tmp_tb where ROWNUM<=");  
	                pageSql.append(page.getStartRow()+page.getPageSize());  
	                pageSql.append(") where row_id>");  
	                pageSql.append(page.getStartRow());  
	            }  
	            return pageSql.toString();  
	        }
	        return sql;  
	    } 
	 
	 private String generateAppPageSql(String sql,AppPager page){  
	     if(page!=null && StringUtil.isNotEmpty(dialect)){  
	         StringBuffer pageSql = new StringBuffer();  
	         if("mysql".equals(dialect)){  
	             pageSql.append(sql);  
	             pageSql.append(" limit "+page.getStartRow()+","+page.getPageSize());  
	         }else if("oracle".equals(dialect)){  
	             pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");  
	             pageSql.append(sql);  
	             pageSql.append("  ) tmp_tb where ROWNUM<=");  
	             pageSql.append(page.getStartRow()+page.getPageSize());  
	             pageSql.append(") where row_id>");  
	             pageSql.append(page.getStartRow());  
	         }  
	         return pageSql.toString();  
	     }
	     return sql;  
	 }  
	 
	 
	
	
	
	 /** <p class="detail">
	 * 功能：
	 * </p>
	 * @param target
	 * @return 
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object) 
	 */ 
	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	
	 /** <p class="detail">
	 * 功能：
	 * </p>
	 * @param properties 
	 * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties) 
	 */ 
	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		dialect = properties.getProperty("dialect");  
        if (StringUtil.isEmpty(dialect)) {  
            throw new NullPointerException("dialect property is not found!");  
        }  
        pageSqlId = properties.getProperty("pageSqlId");  
        if (StringUtil.isEmpty(pageSqlId)) {  
        	throw new NullPointerException("pageSqlId property is not found!");
        }  
	}
	
	 private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException {  
	        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());  
	        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
	        if (parameterMappings != null) {  
	            Configuration configuration = mappedStatement.getConfiguration();  
	            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();  
	            MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);  
	            for (int i = 0; i < parameterMappings.size(); i++) {  
	                ParameterMapping parameterMapping = parameterMappings.get(i);  
	                if (parameterMapping.getMode() != ParameterMode.OUT) {  
	                    Object value;  
	                    String propertyName = parameterMapping.getProperty();  
	                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);  
	                    if (parameterObject == null) {  
	                        value = null;  
	                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {  
	                        value = parameterObject;  
	                    } else if (boundSql.hasAdditionalParameter(propertyName)) {  
	                        value = boundSql.getAdditionalParameter(propertyName);  
	                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {  
	                        value = boundSql.getAdditionalParameter(prop.getName());  
	                        if (value != null) {  
	                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));  
	                        }  
	                    } else {  
	                        value = metaObject == null ? null : metaObject.getValue(propertyName);  
	                    }  
	                    TypeHandler typeHandler = parameterMapping.getTypeHandler();  
	                    if (typeHandler == null) {  
	                        throw new ExecutorException("没有发现TypeHandler参数 "+ propertyName + " 的声明 "+ mappedStatement.getId());  
	                    }  
	                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());  
	                }  
	            }  
	        }  
	    }
	 
	 public static void main(String[] args) {
		  if("queryAppFeedbackListPager".matches(".*ListPage.*")){ 
			  System.out.println("---");
			  
		  }
	}
	
}
