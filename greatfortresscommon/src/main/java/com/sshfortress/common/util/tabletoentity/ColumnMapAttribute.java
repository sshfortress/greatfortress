package com.sshfortress.common.util.tabletoentity;


public class ColumnMapAttribute {
	/**
	 * @param s1 数据库里的表名
	 * @return 去掉下划线并且第一个字母开始大写
	 */
	public static String getBONameFormTable(String s1){
		String tableName="";
		String [] args=s1.split("_");
		for(int i=0;i<args.length;i++){
			String name=args[i];
			if(name!=null && !"".equals(name)){
				tableName=tableName+getStringFristUp(name);
			}
		}
		return tableName;
	}
	/**
	 * @param s1 数据库里的字段
	 * @return 去掉下划线并且第二个字母开始大写
	 */
    public static String getAttibutreNameFormColumn(String s1){
    	String columnName="";
		String [] args=s1.split("_");
		for(int i=0;i<args.length;i++){
			String name=args[i];
			if(name!=null && !"".equals(name)){
				if(i==0){
					columnName=name.toLowerCase();
				}else{
					columnName=columnName+getStringFristUp(name);
				}
			}
		}
		return columnName;
	}
    
    /**
     * 字符串首字母转大写，其他小写
     * @param s
     * @return
     */
    private static String getStringFristUp(String s){
    	String result="";
    	if(s!=null && !"".equals(s)){
    		//
    		char[] chr=s.toCharArray(); 
    		for(int i=0;i<chr.length;i++) {
    			if(i==0 && chr[i]>96){//如果ASCII码>96(小写字母)
    				chr[i]-=32;
    			}
    			if(i>0 && chr[i]<96){//如果ASCII码<96(大写字母)
    				chr[i]+=32; 
    			}
    			result=result+chr[i];
    		}
    	}
    	return result;
    }
    
    /**
     * 字符串首字母转大写，其他不变
     * @param s
     * @return
     */
    public static String getFristUp(String s){
    	String result="";
    	if(s!=null && !"".equals(s)){
    		//
    		char[] chr=s.toCharArray(); 
    		for(int i=0;i<chr.length;i++) {
    			if(i==0 && chr[i]>96){//如果ASCII码>96(小写字母)
    				chr[i]-=32;
    			}
    			result=result+chr[i];
    		}
    	}
    	return result;
    }
    /**
     * 
     * @param columnType oracle字段类型
     * @param size 长度
     * @param scan 小数点后面位数 主要判断number类型
     * @return
     */
    public static String getAttributeType(String columnType,int size,int scan){
    	String attributeType="";
    	if("VARCHAR2".equalsIgnoreCase(columnType)){// 可变字符
    		attributeType="String";
    	}else if("CHAR".equalsIgnoreCase(columnType)){
    		attributeType="String";
    	}else if("NUMBER".equals(columnType)){
    		//System.out.println("jimgdu:"+scan);
    		if(scan>0){
    			attributeType="double";
    		}else{
    			if(size<=8){
    				attributeType="int";
    			}else{
    				attributeType="int";
    			}
    		}
    	}else if("DATE".equals(columnType)){
    		attributeType="Date";
    	}else if("BIGINT".equalsIgnoreCase(columnType)){
    		attributeType="Long";
    	}else if("INTEGER".equalsIgnoreCase(columnType)){
    		attributeType="Integer";
    	}else if("TINYINT".equalsIgnoreCase(columnType)){
    		attributeType="Integer";
    	}else if("INT".equalsIgnoreCase(columnType)){
    		attributeType="Integer";
    	}else if("varchar".equalsIgnoreCase(columnType)){
    		attributeType="String";
    	}else if("timestamp".equalsIgnoreCase(columnType)){
    		attributeType="Date";
    	}else if("decimal".equalsIgnoreCase(columnType)){
    		attributeType="Double";
    	}else if("datetime".equalsIgnoreCase(columnType)){
    		attributeType="Date";
    	}else if("boolean".equalsIgnoreCase(columnType)){
    		attributeType="Boolean";
    	}
    	return attributeType;
    }
    
    public static void main(String args[]){
    	String s="DsFFDSFDFdsad";
    	String s2=getStringFristUp(s);
    	System.out.println(s2);
    	
    }
}
