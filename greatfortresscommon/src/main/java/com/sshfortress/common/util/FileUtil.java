package com.sshfortress.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;


public class FileUtil {
	    //生成文件路径
	    private static String path = "D:\\file\\";
	    
	    //文件路径+名称
	    private static String filenameTemp;
	    /**
	     * 创建文件
	     * @param fileName  文件名称
	     * @param filecontent   文件内容
	     * @return  是否创建成功，成功则返回true
	     */
	    public static boolean createFile(String path,String fileName,String filecontent,String suffix){
	        Boolean bool = false;
	        if(StringUtil.isNullOrEmpty(suffix)){
	        	suffix=".txt";
	        }
	        if(StringUtil.isNullOrEmpty(path)){
	        	path="";
	        }
	        filenameTemp = path+fileName+suffix;//文件路径+名称+文件类型
	        File file = new File(filenameTemp);
	        try {
	            //如果文件不存在，则创建新的文件
	            if(!file.exists()){
	                file.createNewFile();
	                bool = true;
	                System.out.println("success create file,the file is "+filenameTemp);
	                //创建文件成功后，写入内容到文件里
	                writeFileContent(filenameTemp, filecontent);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return bool;
	    }
	    
	    /**
	     * 向文件中写入内容
	     * @param filepath 文件路径与名称
	     * @param newstr  写入的内容
	     * @return
	     * @throws IOException
	     */
	    public static boolean writeFileContent(String filepath,String newstr) throws IOException{
	        Boolean bool = false;
	        String filein = newstr+"\r\n";//新写入的行，换行
	        String temp  = "";
	        
	        FileInputStream fis = null;
	        InputStreamReader isr = null;
	        BufferedReader br = null;
	        FileOutputStream fos  = null;
	        PrintWriter pw = null;
	        try {
	            File file = new File(filepath);//文件路径(包括文件名称)
	            file.setExecutable(true);
	            file.setWritable(true);
	            file.setReadable(true);
	            //将文件读入输入流
	            fis = new FileInputStream(file);
	            isr = new InputStreamReader(fis);
	            br = new BufferedReader(isr);
//	            StringBuffer buffer = new StringBuffer();
//	            
//	            //文件原有内容
//	            for(int i=0;(temp =br.readLine())!=null;i++){
//	                buffer.append(temp);
//	                // 行与行之间的分隔符 相当于“\n”
//	                buffer = buffer.append(System.getProperty("line.separator"));
//	            }
//	            buffer.append(filein);
//	            
//	            fos = new FileOutputStream(file);
//	            pw = new PrintWriter(fos,"UTF-8");
//	            pw.write(buffer.toString().toCharArray());
//	            pw.flush();
	            fos = new FileOutputStream(filepath);
		        OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
	            osw.write(newstr);
	            osw.flush();	            
	            bool = true;
	        } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }finally {
	            //不要忘记关闭
	            if (pw != null) {
	                pw.close();
	            }
	            if (fos != null) {
	                fos.close();
	            }
	            if (br != null) {
	                br.close();
	            }
	            if (isr != null) {
	                isr.close();
	            }
	            if (fis != null) {
	                fis.close();
	            }
	        }
	        return bool;
	    }
	    
	    /**
	     * 删除文件
	     * @param fileName 文件名称
	     * @return
	     */
	    public static boolean delFile(String path,String fileName,String suffix){
	        Boolean bool = false;
	        if(StringUtil.isNullOrEmpty(suffix)){
	        	suffix=".txt";
	        }
	        if(StringUtil.isNullOrEmpty(path)){
	        	path="";
	        }
	        filenameTemp = path+fileName+suffix;
	        File file  = new File(filenameTemp);
	        try {
	            if(file.exists()){
	                file.delete();
	                bool = true;
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return bool;
	    }
	    
	    
	    /**
	     * <p class="detail">
	     * 功能：读取文件内容的情况
	     * </p>
	     * @param fileName
	     */
	    
	    public static String readFileByChars(String path) {
	    	File file = new File(path);
	    	if(!file.exists()){
	    		return "";
	    	}
	    	 StringBuilder result = new StringBuilder();
	         try{
	             BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
	             String s = null;
	             while((s = br.readLine())!=null){//使用readLine方法，一次读一行
	                 //result.append(System.lineSeparator()+s);
	            	 result.append(s);
	             }
	             br.close();    
	         }catch(Exception e){
	             e.printStackTrace();
	         }
	         return result.toString();
	    }
	    
	    
	    /**
	     * 得到文件路径
	     * @return
	     */
	    public static String getFileUrl(){
			
			Map<String,String> m=new HashMap<String,String>();
	    	
		    if(File.separator.equals("\\")){
					m.put("UPLOAD_URL","D:\\uploadfile\\");
			}else{
					m.put("UPLOAD_URL", "/usr/local/tomcat/webapps/greatfortressframework/upload/");
			}
		    return m.get("UPLOAD_URL");
		    
		}
}
