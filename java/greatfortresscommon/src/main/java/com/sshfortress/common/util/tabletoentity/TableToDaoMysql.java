package com.sshfortress.common.util.tabletoentity;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * mysql生成
 */
public class TableToDaoMysql{

	/** 数据库连接URL*/ 
    private final static String DB_URL = "jdbc:mysql://192.168.18.40:3306/zbzx?useUnicode=true&characterEncoding=UTF-8"; 
     
    /** 数据库连接驱动*/ 
    private final static String DB_DRIVER = "com.mysql.jdbc.Driver"; 
     
    /** 数据库用户名*/ 
    private final static String DB_USERNAME = "sshfortress"; 
     
    /** 数据库密码*/ 
    private final static String DB_PASSWORD = "123456";
    
    /** 生成java实体类存放目录*/
    private final static String FILEDIR = "C:\\Users\\sshfortress.org\\Desktop\\daos";
    
    
    /**
     * 生成bean前需修改的值
     */
    public static String db_name="weiside";//数据库名称
    public static String table_name="tb_sys_user";//要生成bean的表名
    public static String java_package_path="com.sshfortress.qt.dao";//生成的bean类package值
    
    
    
	/**功能：主调方法
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 单个表生成bean
		 */
		//exportJavaDao(table_name,java_package_path);
		/**
		 * 批量表生成bean
		 */
		String[] tableArr={
				/*"tb_account_banks",
				"tb_account_banks_card",
				"tb_account_extract",
				"tb_account_extract_check",
				"tb_account_income_expenditure",
				"tb_account_main",
				"tb_account_user_bankcard",
				"tb_address_main",
				"tb_address_user",
				"tb_artificer_change",
				"tb_artificer_check",
				"tb_artificer_detail",
				"tb_artificer_server",
				"tb_artificer_server_change",
				"tb_bus_coupon",
				"tb_bus_coupon_area",
				"tb_bus_coupon_receiver",
				"tb_bus_coupon_type",
				"tb_data_api_pv",
				"tb_qt_oto_agent",
				"tb_qt_oto_agent_area",
				"tb_qt_oto_demand",
				"tb_qt_oto_demand_notice",*/
				"tb_qt_oto_order",
				"tb_qt_oto_order_complain",
				"tb_qt_oto_order_evaluate",
				"tb_qt_oto_order_fee",
				"tb_qt_oto_order_process",
				"tb_qt_oto_order_profit",
				"tb_qt_oto_order_profit_detail",
				"tb_qt_oto_order_promotion",
				"tb_qt_oto_order_serv",
				"tb_qt_oto_order_status"
				/*"tb_qt_oto_pservroj_apply",
				"tb_qt_oto_server",
				"tb_qt_oto_server_check",
				"tb_qt_oto_server_price",
				"tb_qt_oto_servproj",
				"tb_qt_oto_servproj_standard",
				"tb_qt_oto_servproj_standard_value",
				"tb_qt_oto_shop",
				"tb_qt_oto_shop_address",
				"tb_qt_oto_shop_address_change",
				"tb_qt_oto_shop_change",
				"tb_qt_oto_shop_check",
				"tb_qt_oto_shop_tax",
				"tb_qt_oto_shop_trade",
				"tb_qt_oto_shop_trade_change",
				"tb_qt_oto_trade",
				"tb_resource_catalog",
				"tb_resource_file",
				"tb_sys_user_type"*/
		};
		exportBeansBatch(tableArr);
	}

	/**
	 * <p class="detail">
	 * 功能：数据库表批量生成bean
	 * </p>
	 * @param tableNameArr 数据库表名字符串数组
	 */
	public static void exportBeansBatch(String tableNameArr[]){
		for(int i=0; tableNameArr!=null && i<tableNameArr.length; i++){
			try{
				exportJavaDao(tableNameArr[i],java_package_path);
			}catch(Exception e){
				System.out.println("第"+i+"个Dao生成失败，表名："+tableNameArr[i]);
				e.printStackTrace();
			}
		}
	}

	
	public static void exportJavaDao(String tableName,String packageName){
		System.out.println(tableName+"表对应的Dao正在生成...");
		TableEntity entity=getTableEntity(tableName);
		String boName=entity.getBoName();
		List columnAttributes=entity.getColumnAttributes();
		StringBuffer buffer =  new StringBuffer();
		String filePath=FILEDIR+"//"+boName+"Dao.java";
		try {
			if(packageName==null || "".equals(packageName)){
				buffer.append("\n");
			}else{
				buffer.append("package "+packageName+";\n\n");
			}
			buffer.append("import org.springframework.stereotype.Repository;\n");
			buffer.append("import ").append(packageName.substring(0,packageName.length()-4)).append(".beans.").append(entity.getBoName()).append(";\n");
			buffer.append("import com.sshfortress.comm.dao.BaseDao;\n");
			
			buffer.append("\n@Repository\n");
			buffer.append("public class "+boName+"Dao extends BaseDao{\n");

			writeDaoMethod(buffer,tableName,entity);
			
			buffer.append("}\n");
			File beanDir=new File(FILEDIR);
			if(!beanDir.isDirectory()){
				beanDir.mkdir();
			}
			File beanFile=new File(filePath);
			if(!beanFile.exists()){
				beanFile.createNewFile();
			}
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");
			out.write(buffer.toString());
			out.flush();
			out.close();
			System.out.println(tableName+"表对应的dao生成成功："+filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 功能：获取数据库所有表名
	 * @param tableName
	 * @return
	 */
	public static TableEntity getTableEntity(String tableName){
    	TableEntity entity=new TableEntity();
    	List columnAttributes=new ArrayList();
    	//当前用户有权限查看的所有表的表名
    	PreparedStatement pstm=null;
    	Connection con=null;
    	ResultSetMetaData rsm=null; 
    	String sql="SELECT * FROM "+tableName+" limit 0,1";

		try {
			con=getConnection();
			DatabaseMetaData dbMeta = con.getMetaData();
			ResultSet pkRSet=dbMeta.getPrimaryKeys(null,null,tableName);
			String primaryKey="";
			while(pkRSet.next()){
				primaryKey=pkRSet.getString(4);
				break;
			}
			entity.setPrimaryKeyName(primaryKey);
			entity.setPrimaryIdName(ColumnMapAttribute.getAttibutreNameFormColumn(primaryKey));
			pstm = con.prepareStatement(sql);
			pstm.executeQuery();
			rsm=pstm.getMetaData();
			for(int i=1;i<=rsm.getColumnCount();i++){
				ColumnAttribute columnAttribute=new ColumnAttribute();
				// 字段名
				String columnName=rsm.getColumnName(i);
				columnAttribute.setColumnName(columnName);
				if(columnName.equals(primaryKey)){
					columnAttribute.setPK(true);
				}else{
					columnAttribute.setPK(false);
				}
				// 字段类型
				String columnType=rsm.getColumnTypeName(i);
				if("CLOB".equals(columnType)){
					continue;
				}
				columnAttribute.setColumType(columnType);
				// 字段长度
				int columnSize=rsm.getPrecision(i);
				columnAttribute.setLength(columnSize);
				// 获取指定列的小数点右边的位数。
				int scale=rsm.getScale(i);
				//System.out.println("字段名称： "+rsm.getColumnLabel(i)+"\n\t sql类型编号："+rsm.getColumnType(i)+"\n\t sql类型名称："+rsm.getColumnTypeName(i)+"\n\t scale:"+rsm.getScale(i)+"\n\t sql类型精确度："+rsm.getPrecision(i)+"\n\t 最大字符个数："+rsm.getColumnDisplaySize(i)+"\n\t java类型："+rsm.getColumnClassName(i));
				columnAttribute.setScale(scale);
				// 是否必填
				int isNullable=rsm.isNullable(i);
				columnAttribute.setNullable(isNullable);
				columnAttribute.setAttributeName(ColumnMapAttribute.getAttibutreNameFormColumn(columnName));
				columnAttribute.setAttributeType(ColumnMapAttribute.getAttributeType(columnType,columnSize,scale));
				// 字段描述
				String coments=getColumnComments(con,tableName,columnName);
				columnAttribute.setComment(coments);
				
				columnAttributes.add(columnAttribute);
			}
			entity.setBoName(ColumnMapAttribute.getBONameFormTable(tableName));
			entity.setColumnAttributes(columnAttributes);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//closeConn(pstm,null,con);
		}
		return entity;
    }
	/**
	 * 功能：获得字段说明
	 * @param con
	 * @param tableName
	 * @param columnName
	 * @return
	 * @throws SQLException
	 * @throws Exception 
	 */
	private static String getColumnComments(Connection con, String tableName, String columnName) throws SQLException {
    	//String sql=" select t.TABLE_NAME,t.COLUMN_NAME,t.comments  from user_col_comments t where t.table_name=? and t.column_name=? ";
    	String sql="SELECT t.`TABLE_NAME`,t.`COLUMN_NAME`,t.`COLUMN_COMMENT` FROM information_schema.columns t WHERE t.`TABLE_SCHEMA`='"+db_name+"' AND t.table_name='"+tableName+"' and t.`COLUMN_NAME`='"+columnName+"' ORDER BY t.table_schema,t.table_name";
    	PreparedStatement pstm=con.prepareStatement(sql);

		ResultSet rt = pstm.executeQuery();
		String colDesc="";
		if(rt.next()){
			colDesc=rt.getString(3);
		}
    	return colDesc;
	}
	
	private static void writeDaoMethod(StringBuffer writer,String tableName,TableEntity entity) throws IOException{
		writer.append("\n	public boolean add").append(entity.getBoName()).append("(").append(entity.getBoName()).append(" ").append(getEntityPramName(entity.getBoName())).append("){");
		writer.append("\n		return super.save(").append(getEntityPramName(entity.getBoName())).append(");");
		writer.append("\n	}\n\n");

		writer.append("	public boolean del").append(entity.getBoName()).append("(").append("Long id").append("){");
		writer.append("\n		return super.deleteByKey(\"").append(tableName).append("\",id);");
		writer.append("\n	}\n\n");
		
		writer.append("	public boolean update").append(entity.getBoName()).append("(");
		writer.append(entity.getBoName()).append(" ").append(getEntityPramName(entity.getBoName())).append("){");
		writer.append("\n		return super.update(").append(getEntityPramName(entity.getBoName())).append(");");
		writer.append("\n	}\n\n");
		
		writer.append("	public ").append(entity.getBoName()).append(" get").append(entity.getBoName()).append("(Long id){");
		writer.append("\n		return (").append(entity.getBoName()).append(")super.get(").append(entity.getBoName()).append(".class,id);");
		writer.append("\n	}\n\n");
		
	}
	public static String getEntityPramName(String beanName){
		if(beanName==null){
			return "";
		}
		return beanName.substring(0, 1).toLowerCase()+beanName.substring(1);
	}
	/**
	 * 功能：bo类set和get方法的生成，包括各个属性
	 * @param writer
	 * @param columnAttributes
	 * @throws IOException
	 */
	private static void writeSetGet(StringBuffer writer,List columnAttributes) throws IOException{
		for(int i=0;i<columnAttributes.size();i++){
			writer.append("\n");
			ColumnAttribute columnAttribute=(ColumnAttribute)columnAttributes.get(i);
			String attributeName=columnAttribute.getAttributeName();
			String attributeNameUP=ColumnMapAttribute.getFristUp(attributeName);
			String attributeType=columnAttribute.getAttributeType();
			//set方法
			if(((ColumnAttribute)columnAttributes.get(i)).isPK()){
				writer.append("\t   @Id\n");
				writer.append("\t   @GeneratedValue(strategy = IDENTITY)\n");
				writer.append("\t   @Column(name = \"id\", unique = true, nullable = false)\n");
			}else{
				writer.append("\t   @Column(name =\""+((ColumnAttribute)columnAttributes.get(i)).getColumnName()+"\")\n");
			}
			writer.append("       public "+attributeType+" get"+attributeNameUP+"(){\n");
			writer.append("              return this."+attributeName+";\n");
			writer.append("       }");
			writer.append("\n");
			//get方法
			writer.append("       public void set"+attributeNameUP+"("+attributeType+" "+attributeName+"){\n");
			writer.append("              this."+attributeName+"="+attributeName+";\n");
			writer.append("       }");
			writer.append("\n");
		}
	}
	/**
	 * 功能：关闭数据库链接
	 * @param stmt
	 * @param rt
	 * @param dbConn
	 */
	public static void closeConn(Statement stmt,ResultSet rt,Connection dbConn){
		try {
			if(stmt!=null && stmt.isClosed()){
				stmt.close();
			}
			if(rt!=null && rt.isClosed()){
				rt.close();
			}
			if(dbConn!=null && dbConn.isClosed()){
				dbConn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 功能：获得数据库链接
	 * @return
	 */
	public static Connection getConnection(){
        Connection conn = null; 
        try{ 
        	//注册驱动
            Class.forName(DB_DRIVER); 
            conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD); 
        }catch(Exception ex){ 
            ex.printStackTrace(); 
        } 
        return conn; 
    }

}

